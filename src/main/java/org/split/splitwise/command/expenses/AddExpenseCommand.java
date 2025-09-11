package org.split.splitwise.command.expenses;

import org.split.splitwise.command.CommandKeywords;
import org.split.splitwise.command.ICommand;
import org.split.splitwise.dtos.SplitType;
import org.split.splitwise.command.utils.CommandUtils;
import org.split.splitwise.controllers.ExpenseController;
import org.split.splitwise.dtos.AddExpenseRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.split.splitwise.dtos.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class AddExpenseCommand implements ICommand {

    private final ExpenseController expenseController;

    @Autowired
    public AddExpenseCommand(ExpenseController expenseController) {
        this.expenseController = expenseController;
    }

    @Override
    public boolean matches(String command) {
        List<String> words = CommandUtils.splitWords(command);
        boolean matches = words.contains(CommandKeywords.EXPENSE);
        if (!matches) {
            System.out.println("Invalid Command ,<u1> Expense <g1> iPay 1000 Equal Desc Wifi Bill ");
            System.out.println("Example for Usage :u1 Expense u2 u3 u4 iPay 1000 Equal Desc Last night dinner");
        }
        return matches;
    }

    @Override
    public void execute(String command) {
        List<String> tokens = CommandUtils.splitWords(command);
        if (tokens.size() < 6) {
            System.out.println("Incomplete Command");
            return;
        }

        AddExpenseRequestDTO request = null;
        try {
            request = convert(tokens);
        } catch (Exception e) {
            System.out.println("Failed to execute command: " + e.getMessage());
            return;
        }

        this.expenseController.addExpense(request);

    }

    private AddExpenseRequestDTO convert(List<String> tokens) {
        AddExpenseRequestDTO requestDTO = new AddExpenseRequestDTO();
        long createdUserId = Long.parseLong(tokens.get(0));
        int i = 2; // skip 'Expense'
        List<Long> users = new ArrayList<>();
        List<Pair<Long,Integer>> paidUsers = new ArrayList<>();

        users.add(createdUserId);
        List<Long> paidUserIds = new ArrayList<>();
        long groupId = 0L;
        // Participants or group

        if (tokens.contains(CommandKeywords.I_PAY)) {

            while (!tokens.get(i).equalsIgnoreCase(CommandKeywords.I_PAY)) {
                if (tokens.get(i).startsWith(CommandKeywords.GROUP)) {
                    i++;
                    groupId = parseLong(tokens.get(i++), CommandKeywords.GROUP);
                    break;
                }
                users.add(parseLong(tokens.get(i++), CommandKeywords.USER));
            }
            i++; // skip 'iPay'
            double amount = parseDouble(tokens.get(i++), "amount");
            paidUsers.add(new Pair(createdUserId, amount));
        } else if (tokens.contains(CommandKeywords.MULTI_PAY)) {
            paidUserIds.add(createdUserId);
            while (!tokens.get(i).equalsIgnoreCase(CommandKeywords.MULTI_PAY)) {
                long userId = parseLong(tokens.get(i++), CommandKeywords.USER);
                users.add(userId);
                paidUserIds.add(createdUserId);
            }
            i++;  //slip 'MultiPay'
            // int count=0;
            int j=0;
            while (j < paidUserIds.size()) {
                double amount = parseDouble(tokens.get(i++), "amount");
                paidUsers.add(new Pair(paidUserIds.get(j), amount));
                j++;
            }

        } else {
            throw new IllegalArgumentException("Missing 'iPay' or 'MultiPay' keyword.");
        }


        SplitType splitType = SplitType.fromString(tokens.get(i++));
        List<Integer> splitValues = new ArrayList<>();

        if (splitType == SplitType.PERCENT || splitType == SplitType.RATIO || splitType == SplitType.EXACT) {
            int numParticipants = users.size();
            for (int j = 0; j < numParticipants; j++) {
                splitValues.add(parseInt(tokens.get(i++), splitType.name() + " value"));
            }
        }

        if (!tokens.get(i++).equalsIgnoreCase(CommandKeywords.DESC))
            throw new IllegalArgumentException("Missing 'Desc' keyword.");
        i++; // skip 'Desc'
        StringBuilder description = new StringBuilder();
        for (int j = i; j < tokens.size(); j++) {
            description.append(tokens.get(j));
            description.append(" ");
        }

        description.deleteCharAt(description.length() - 1);
        requestDTO.setDescription(description.toString());
        requestDTO.setGroupId(groupId);
        requestDTO.setUsers(users);
        requestDTO.setCreatedUserId(createdUserId);
        requestDTO.setPaidUsers(paidUsers);
        requestDTO.setSplitValues(splitValues);
        requestDTO.setSplitType(splitType);
        return requestDTO;
    }

    private long parseLong(String s, String context) {
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid " + context + ": " + s);
        }
    }

    private double parseDouble(String s, String context) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid " + context + ": " + s);
        }
    }

    private Integer parseInt(String s, String context) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid " + context + ": " + s);
        }
    }
}
