package prateek_gupta.foody.util;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

import prateek_gupta.foody.models.Result;

public class RecipesDiffUtils extends DiffUtil.Callback {
    List<Result> oldList;
    List<Result> newList;

    public RecipesDiffUtils(List<Result> oldList, List<Result> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition)==newList.get(newItemPosition);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition)==newList.get(newItemPosition);
    }
}
