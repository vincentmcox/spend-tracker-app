package indalosoftworks.spendtracker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Extension of the BaseExpandableListAdapter class for the implementation of the expandable list in
 * the MainActivity
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<Envelope> envelopes;
    private SpendApp app;

    public ExpandableListAdapter(Context context, EnvelopeHolder envelopeHolder, SpendApp app)
    {
        this.context = context;
        this.envelopes = envelopeHolder.getEnvelopes();
        this.app = app;
    }


    @Override
    public int getGroupCount() {
        return envelopes.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return envelopes.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return envelopes.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.listview_parent, parent, false);
        }
        TextView envelopeName = (TextView) v.findViewById(R.id.txt_parent_envelopename);
        TextView remainingAmount = (TextView) v.findViewById(R.id.txt_parent_quantity);

        Envelope thisEnvelope = envelopes.get(groupPosition);

        envelopeName.setText(thisEnvelope.getEnvelopeName());
        remainingAmount.setText(thisEnvelope.getRemainingAmount());

        return v;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, final View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.listview_child, parent, false);
        }
        TextView weeklyAmount = (TextView) v.findViewById(R.id.txt_weeklybudget_quantity);
        TextView spentAmount = (TextView) v.findViewById(R.id.txt_spent_quantity);
        TextView remainingAmount = (TextView) v.findViewById(R.id.txt_remaining_quantity);
        Button spendButton = (Button) v.findViewById(R.id.btn_spend);
        Button edit = (Button) v.findViewById(R.id.btn_edit);

        final Envelope thisEnvelope = envelopes.get(groupPosition);

        weeklyAmount.setText(thisEnvelope.getBudgetAmount());
        spentAmount.setText(thisEnvelope.getSpentAmount());
        remainingAmount.setText(thisEnvelope.getRemainingAmount());

        //Button listeners
        spendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Start an input dialogue & get the spend amount
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                builder.setTitle("Enter an amount");
                builder.setView(inflater.inflate(R.layout.spend_dialogue, null))
                        .setPositiveButton(R.string.btn_str_done, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                View view = convertView;
                                //User clicked OK button
                                // Get the info and put it in the app.
                                EditText spendIn = (EditText) view.findViewById(R.id.input_spend_dialogue);
                                app.spend(groupPosition, Double.parseDouble(spendIn.getText().toString()));
                        }
                    })
                        .setNegativeButton(R.string.btn_str_cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // User cancelled the dialogue
                            }
                        });
                builder.create();

            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                builder.setTitle("Edit details");
                builder.setView(inflater.inflate(R.layout.edit_dialogue, null))
                        .setPositiveButton(R.string.btn_str_done, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                View view = convertView;
                                // User clicked OK button
                                // Get the info and put it in the app.
                                EditText newBudgetIn = (EditText) view.findViewById(R.id.input_budget_dialogue_amount);
                                EditText newName = (EditText) view.findViewById(R.id.input_envelope_dialogue_name);
                                String budgetNumber = newBudgetIn.getText().toString();
                                String nameNew = newName.getText().toString();
                                if(budgetNumber.equals("") || budgetNumber.equals("0"))
                                {
                                    budgetNumber = thisEnvelope.getBudgetAmount();
                                }
                                app.spend(groupPosition, Double.parseDouble(budgetNumber));
                            }
                        })
                        .setNegativeButton(R.string.btn_str_cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // User cancelled the dialogue
                            }
                        });
                builder.create();;
            }
        });


        return v;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
