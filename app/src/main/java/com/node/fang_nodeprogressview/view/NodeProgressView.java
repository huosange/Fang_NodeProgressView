package com.node.fang_nodeprogressview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

import com.node.fang_nodeprogressview.R;

import java.util.List;

public class NodeProgressView extends View {

    private float width;//轴线的宽度
    private float nodeRadius;//圆形的半径

    private Paint linePaint;//画轴线的
    private Paint paint;//画时间的
    private TextPaint textPaint;//画内容的

    private int nodeInterval;//节点间隔
    private int top = 50;//距上边的距离
    private int left = 50;//距左边的距离

    private int screen_width;//屏幕的宽度

    private Context context;
    private NodeProgressAdapter adapter;

    public NodeProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NodeProgressView);
        width = typedArray.getDimension(R.styleable.NodeProgressView_width, 20);
        nodeRadius = typedArray.getDimension(R.styleable.NodeProgressView_nodeRadius, 10);
        init();
    }

    private void init() {
        linePaint = new Paint();
        linePaint.setColor(getResources().getColor(R.color.nodeColor));
        linePaint.setAntiAlias(true);

        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.nodeTextColor));
        paint.setAntiAlias(true);
        paint.setTextSize(30);

        textPaint = new TextPaint();
        textPaint.setColor(getResources().getColor(R.color.nodeTextColor));
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(35);

        nodeInterval = dip2px(context, 100);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        screen_width = wm.getDefaultDisplay().getWidth();
    }

    public void setNodeProgressAdapter(NodeProgressAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (adapter == null || adapter.getCount() == 0) {
            return;
        }
        List<LogisticsData> data = adapter.getData();
        canvas.drawRect(left, top, width + left, adapter.getCount() * nodeInterval + top, linePaint);
        for (int i = 0; i < adapter.getCount(); i++) {
            if (i == 0) {
                canvas.drawText(data.get(i).getTime(), left * 2 + width, (i + 1) * nodeInterval + top - 20, paint);
                //文字换行
                StaticLayout layout = new StaticLayout(data.get(i).getContent(), textPaint, (int) (screen_width * 0.8), Layout.Alignment.ALIGN_NORMAL, 1.0f, 1.0f, true);
                canvas.save();
                canvas.translate(left * 2 + 10, i * nodeInterval + top + 20);
                layout.draw(canvas);
                canvas.restore();
                //画圆
                canvas.drawCircle(left + width / 2, i * nodeInterval + top, nodeRadius, paint);
            } else {
                paint.setColor(getResources().getColor(R.color.nodeColor));
                canvas.drawText(data.get(i).getTime(), left * 2 + width, (i + 1) * nodeInterval + top - 20, paint);
                //文字换行
                textPaint.setColor(getResources().getColor(R.color.nodeColor));
                StaticLayout layout = new StaticLayout(data.get(i).getContent(), textPaint, (int) (screen_width * 0.8), Layout.Alignment.ALIGN_NORMAL, 1.0f, 1.0f, true);
                canvas.save();
                canvas.translate(left * 2 + 10, i * nodeInterval + top + 20);
                layout.draw(canvas);
                canvas.restore();
                //画圆
                canvas.drawCircle(width / 2 + left, i * nodeInterval + top, nodeRadius, paint);
                canvas.drawLine(left * 2 + 10, i * nodeInterval + top, screen_width, i * nodeInterval + top, paint);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (adapter == null || adapter.getCount() == 0) {
            return;
        }
        setMeasuredDimension(widthMeasureSpec, adapter.getCount() * nodeInterval + top);
    }

    /**
     * dp转px
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
