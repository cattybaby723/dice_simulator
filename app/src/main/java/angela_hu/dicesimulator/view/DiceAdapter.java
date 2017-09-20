package angela_hu.dicesimulator.view;

import android.arch.lifecycle.LiveData;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import angela_hu.dicesimulator.R;
import angela_hu.dicesimulator.model.Dice;

/**
 * @author anrou_hu
 */

public class DiceAdapter extends RecyclerView.Adapter {


    private LiveData<List<Dice>> mDices;


    public DiceAdapter(LiveData<List<Dice>> dices) {
        mDices = dices;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_dice, parent, false);
        return new DiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        List<Dice> dices = mDices.getValue();
        if (dices == null) return;

        Dice dice = dices.get(position);
        ((DiceViewHolder) holder).bindView(dice);
    }

    @Override
    public int getItemCount() {
        List<Dice> dices = mDices.getValue();
        return (dices == null) ? 0 : dices.size();
    }
}
