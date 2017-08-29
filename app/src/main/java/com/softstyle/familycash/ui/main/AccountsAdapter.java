package com.softstyle.familycash.ui.main;

/**
 * Created by ap on 29.08.2017.
 */


import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.softstyle.familycash.data.model.Account;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.softstyle.familycash.R;

public class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.AccountViewHolder> {

    private List<Account> mAccounts;

    @Inject
    public AccountsAdapter() {
        mAccounts = new ArrayList<>();
    }

    public void setRibots(List<Account> accs) {
        mAccounts = accs;
    }

    @Override
    public AccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_account, parent, false);
        return new AccountViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AccountViewHolder holder, int position) {
        Account account = mAccounts.get(position);
        //holder.hexColorView.setBackgroundColor(Color.parseColor(account.profile().hexColor()));
        holder.nameTextView.setText(String.format("%s %s",
                account.name(), account.amount()));
        holder.emailTextView.setText(account.currency());
    }

    @Override
    public int getItemCount() {
        return mAccounts.size();
    }

    class AccountViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.view_hex_color) View hexColorView;
        @BindView(R.id.text_name) TextView nameTextView;
        @BindView(R.id.text_email) TextView emailTextView;

        public AccountViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
