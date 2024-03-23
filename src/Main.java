import java.io.IOException;
import java.util.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.charset.Charset;


public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(new File("C:\\Users\\Kamil\\IdeaProjects\\Sabirzyanov_Kamil_11-304_KR\\src\\schedule.txt"));
        List<String> list = new ArrayList<String>();
        while (scanner.hasNext()){
            list.add(scanner.next());
        }
        scanner.close();
        list = Files.readAllLines(new File("C:\\Users\\Kamil\\IdeaProjects\\Sabirzyanov_Kamil_11-304_KR\\src\\schedule.txt").toPath(), Charset.defaultCharset());

        Map<String, List<Program>> programs = new HashMap<>();
        List<Program> allProgramList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).charAt(0) == '#') {
                String currentChannel = list.get(i);
                i++;
                while (list.get(i).charAt(0) != '#'){
                    Program currentProgramm = new Program(currentChannel, parseTime(list.get(i)), list.get(i + 1));
                    allProgramList.add(currentProgramm);
                    if (programs.containsKey(currentChannel)){
                        programs.get(currentChannel).add(currentProgramm);
                    }
                    else{
                        programs.put(currentChannel, new ArrayList<>(List.of(currentProgramm)));
                    }
                    i = i + 2;
                    if (i >= list.size()){
                        break;
                    }
                }
                i = i - 1;
            }
        }


    }

    public static void currentTimeProgramPrinting(BroadcastsTime currentTime, List<Program> allProgramList) {
        for (int i = 0; i < allProgramList.size(); ++i) {
            if (allProgramList.get(i).getTime().equals(currentTime)) {
                System.out.println(allProgramList.toString());
            }
        }
    }

    public static void currentNameProgramPrinting(String currentName, List<Program> allProgramList) {
        for (int i = 0; i < allProgramList.size(); ++i) {
            if (allProgramList.get(i).getName().equals(currentName)) {
                System.out.println(allProgramList.toString());
            }
        }
    }

    public static void currentNameProgramPrinting(String currentChannel,BroadcastsTime currentTime, List<Program> allProgramList) {
        for (int i = 0; i < allProgramList.size(); ++i) {
            if (allProgramList.get(i).getChannel().equals(currentChannel) & allProgramList.get(i).getTime().equals(currentTime)) {
                System.out.println(allProgramList.toString());
            }
        }
    }

    private static BroadcastsTime parseTime(String timeStr) {
        String[] parts = timeStr.split(":");
        byte hour = Byte.parseByte(parts[0]);
        byte minutes = Byte.parseByte(parts[1]);
        return new BroadcastsTime(hour, minutes);
    }
}