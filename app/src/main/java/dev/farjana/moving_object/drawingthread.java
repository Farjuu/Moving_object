package dev.farjana.moving_object;

/**
 * Created by Farjana on 8/1/2017.
 */
        import static java.lang.Thread.sleep;

        import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.Canvas;
        import android.graphics.Point;
        import android.view.Display;
        import android.view.WindowManager;

public class drawingthread extends Thread {

    private Canvas canvas;
    private Gameview gameView;
    private Context context;

    boolean threadFlag = false;

    Bitmap backgroundBitmap;
    int displayX,displayY;



    public drawingthread( Gameview gameView, Context context) {
        super();
        this.gameView = gameView;
        this.context = context;

        initializeAll();
    }

    private void initializeAll() {

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = windowManager.getDefaultDisplay();

        Point displayDimension = new Point();

        defaultDisplay.getSize(displayDimension);

        displayX = displayDimension.x;
        displayY= displayDimension.y;

        backgroundBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.index);
        backgroundBitmap= Bitmap.createScaledBitmap(backgroundBitmap, displayX, displayY, true);


    }



    @Override
    public void run() {

        threadFlag = true;

        while(threadFlag){
            canvas = gameView.surfaceholder.lockCanvas();

            try {
                synchronized (gameView.surfaceholder) {

                    updateDisplay();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally{
                if(canvas!=null){
                    gameView.surfaceholder.unlockCanvasAndPost(canvas);
                }
            }

            try {
                sleep(17);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void updateDisplay() {
        canvas.drawBitmap(backgroundBitmap, 0, 0, null);

    }

    public void stopThread() {

        threadFlag= false;

    }
}