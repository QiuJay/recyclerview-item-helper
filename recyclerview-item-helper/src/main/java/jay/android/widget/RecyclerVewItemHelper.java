package jay.android.widget;

import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by jay on 2017/8/19 下午8:00
 *
 * @author jay
 */
public class RecyclerVewItemHelper {

    private final RecyclerView mView;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public RecyclerVewItemHelper(RecyclerView view) {
        mView = view;

        mView.addOnItemTouchListener(new OnItemClickTouchListener());
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        mOnItemLongClickListener = listener;
    }


    private class OnItemClickTouchListener implements RecyclerView.OnItemTouchListener {
        private GestureDetector mGestureDetector = new GestureDetector(mView.getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent ev) {
                if (mOnItemClickListener != null) {

                    View view = mView.findChildViewUnder(ev.getX(), ev.getY());
                    int position = mView.getChildAdapterPosition(view);

                    mOnItemClickListener.onItemClick(mView.getAdapter(), view, position);
                    return true;
                }
                return false;
            }

            @Override
            public void onLongPress(MotionEvent ev) {
                if (mOnItemLongClickListener != null) {

                    View view = mView.findChildViewUnder(ev.getX(), ev.getY());
                    int position = mView.getChildAdapterPosition(view);

                    mOnItemLongClickListener.onItemLongClick(mView.getAdapter(), view, position);
                }
            }
        });

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            return mGestureDetector.onTouchEvent(e);
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    public interface OnItemClickListener {
        void onItemClick(RecyclerView.Adapter adapter, View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(RecyclerView.Adapter adapter, View view, int position);
    }
}
