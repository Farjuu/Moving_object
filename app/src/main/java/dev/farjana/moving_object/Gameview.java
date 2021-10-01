package dev.farjana.moving_object;

/**
 * Created by Farjana on 8/1/2017.
 */

        import android.annotation.SuppressLint;
        import android.content.Context;
        import android.view.MotionEvent;
        import android.view.SurfaceHolder;
        import android.view.SurfaceHolder.Callback;
        import android.view.SurfaceView;

public class Gameview extends SurfaceView implements Callback{
    Context context;
    final SurfaceHolder surfaceholder;
    drawingthread drawingthread;


    public Gameview(Context context) {
        super(context);
        this.context=context;

        surfaceholder = getHolder();
        surfaceholder.addCallback(this);

        drawingthread = new drawingthread(this, context);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            drawingthread.start();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            restartThread();
        }

    }

    private void restartThread() {
        // TODO Auto-generated method stub
        drawingthread.stopThread();
        drawingthread= null;
        drawingthread= new drawingthread(this, context);
        drawingthread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        drawingthread.stopThread();

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        return super.onTouchEvent(event);
    }


}

