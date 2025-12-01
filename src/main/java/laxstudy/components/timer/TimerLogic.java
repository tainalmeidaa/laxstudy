package laxstudy.components.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

public class TimerLogic {
	
	private Timer timer = new Timer();
    private Calendar timenow = Calendar.getInstance();
    private Calendar timeZero = Calendar.getInstance();

    private boolean running = false;

    private Consumer<String> onTick;
    private Runnable onFlash;

    public TimerLogic(Consumer<String> onTick, Runnable onFlash) {
        this.onTick = onTick;
        this.onFlash = onFlash;

        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public void setTime(String hhmmss) throws ParseException {
        
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        timenow.setTime(sdf.parse(hhmmss));
        timeZero.setTime(sdf.parse("00:00:00"));
    }

    public void start() { running = true; }
    public void pause() { running = false; }

    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            if (!running) return;

            if (timenow.compareTo(timeZero) > 0) {

                timenow.set(Calendar.SECOND, timenow.get(Calendar.SECOND) - 1);

                long diff = timenow.getTimeInMillis() - timeZero.getTimeInMillis();

                if (diff <= 10000) {
                    onFlash.run();
                }

                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                onTick.accept(formatter.format(timenow.getTime()));
            } else {
                running = false;
            }
        }
    };

    public void stopTimer() {
        timer.cancel();
    }
}
