package angela_hu.dicesimulator.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import angela_hu.dicesimulator.R;

/**
 * @author anrou_hu
 */

public class DiceViewHolder extends RecyclerView.ViewHolder {

    private TextView mDice;

    public DiceViewHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        mDice = (TextView) itemView.findViewById(R.id.dice);
    }
}
