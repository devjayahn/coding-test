package programmers.pccp.level.one;

public class VideoPlayer {

    /**
     * String video_len, String pos, String op_start, String op_end, String[] commands
     * video_len  pos	op_start op_end	commands	      result
     * "34:33"	"13:00"	"00:55"	"02:55"	["next", "prev"]	"13:00"
     * "10:55"	"00:05"	"00:15"	"06:55"	["prev", "next", "next"]	"06:55"
     * "07:22"	"04:05"	"00:15"	"04:07"	["next"]	"04:17"
     */

    public static final String COMMAND_PREV = "prev";
    public static final String COMMAND_NEXT = "next";
    public static final String MINIMUM_POS = "00:00";
    public static final int COMMAND_TERM = 10;

    public static void main(String[] args) {

    }

    private String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";

        int convertedVideoLen = convertTimeStringToSecond(video_len);
        int convertedPos = convertTimeStringToSecond(pos);
        int convertedOpStart = convertTimeStringToSecond(op_start);
        int convertedOpEnd = convertTimeStringToSecond(op_end);
        int posSecond = convertedPos;

        for (String command : commands) {
            posSecond = calcAfterPos(command, convertedVideoLen, posSecond, convertedOpStart, convertedOpEnd);
            System.out.println("POS SECOND : " + posSecond);
        }

        return answer;
    }

    private int convertTimeStringToSecond(String time) {
        String[] minuteSecondSplit = time.split(":");
        int minute = Integer.parseInt(minuteSecondSplit[0]);
        int second = Integer.parseInt(minuteSecondSplit[1]);
        return minute * 60 + second;
    }

    private int calcAfterPos(String command, int videoLen, int pos, int opStart, int opEnd) {
        if (COMMAND_PREV.equals(command)) {
            int minusTerm = pos - COMMAND_TERM;
            return minusTerm > 0 ? minusTerm : 0;
        } else if (COMMAND_NEXT.equals(command)) {
            int plusTerm = pos + COMMAND_TERM;
            if (plusTerm < opStart) {
                return opStart;
            } else {
                return plusTerm > videoLen ? videoLen : plusTerm;
            }
        } else {
            return videoLen;
        }
    }
}
