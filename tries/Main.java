public class Main {

    public static void main(String[] args) {
        Tries tries = new Tries();
        String[] paths = {"/yang/file1/a", "/yang/file2/a", "/yang/file1/b", "/opt/topus/spo"
        , "/opt/topus/iot", "/home/yang/topus", "/root/core"};
        for (int i = 0; i < paths.length; ++i) {
            tries.put(paths[i], Integer.toString(i));
        }
        tries.Collection();

        for (int i = 0; i < paths.length; ++i) {
            String value = tries.getValue(paths[i]);
            System.out.println(paths[i] + " : " + value);
        }
    }
}
