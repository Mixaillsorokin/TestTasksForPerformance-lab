package task1;

public class Task1 {
    public static void main(String[] args) {
        if (args.length < 2) {
//            System.out.println("Необходимо передать два аргумента: n и m");
            return;
        }

        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        StringBuilder path = new StringBuilder();
        int currentPosition = 1;

        for (int i = 0; i < n; i++) {
            int nextPosition = (currentPosition + m - 1) % n;
            if (nextPosition == 0) nextPosition = n;
            path.append(currentPosition);

            currentPosition = nextPosition;
        }

        System.out.print(path);
    }
}

