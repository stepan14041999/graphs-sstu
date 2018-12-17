package ru.stepan1404;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {
    public static void main(String... args) {
        if (args.length > 0) {
            try {
                int[] digits = Arrays.stream(args).mapToInt(Integer::parseInt).toArray();
                int[] dots = new int[digits.length + 2];
                Arrays.setAll(dots, i -> i + 1);
                int[][] lines = new int[digits.length + 1][2];

                List<Integer> digitsCache = IntStream.of(digits).boxed().collect(Collectors.toList());
                List<Integer> dotsCache = IntStream.of(dots).boxed().collect(Collectors.toList());
                for (int i = 0; i < digits.length; i++) {
                    int min = Integer.MAX_VALUE;

                    for (int dot : dotsCache) {
                        if (dot < min) {
                            if (digitsCache.stream().allMatch(digit -> digit != dot)) {
                                min = dot;
                                break;
                            }
                        }
                    }

                    dotsCache.remove(new Integer(min));
                    digitsCache.remove(new Integer(digits[i]));
                    lines[i][0] = min;
                    lines[i][1] = digits[i];
                }

                lines[digits.length][0] = dotsCache.get(0);
                lines[digits.length][1] = dotsCache.get(1);

                Map<Integer, Set<Integer>> multimap = new HashMap<>();

                IntStream.of(dots).forEach(dot -> multimap.put(dot, new HashSet<>()));

                for (int[] line : lines) {
                    int a = line[0];
                    int b = line[1];

                    multimap.get(a).add(b);
                    multimap.get(b).add(a);
                }

                for (Map.Entry<Integer, Set<Integer>> entry : multimap.entrySet()) {
                    System.out.print(String.format("%d: ", entry.getKey()));
                    entry.getValue().forEach(value -> System.out.print(String.format("%d ", value)));
                    System.out.println();
                }
            } catch (NumberFormatException e) {
                System.err.println("String must be numeric");
            }
        } else {
            System.err.println("Enter more digits");
        }
    }
}
