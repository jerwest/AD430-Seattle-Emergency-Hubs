package com.cap.seattleemergencyhubs;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableTextViewAdapter extends BaseExpandableListAdapter {

    Context context;

    String[] faqs={
            "Question number 1",
            "Question number 2",
            "Question number 3"
    };

    String[][] answer={
            {"Answer to question number 1, Answer to question number 1Answer to question number 1, Answer to question number 1\n\n"+
                    "Answer to question number 1Answer to question number 1 Answer to question number 1\n"+
                    "Answer to question number 1 Answer to question number 1 Answer to question number 1 Answer to question number 1"},


            {"Answer to question number 2, bla bla bla, Answer to question number 2, bla bla bla, Answer to question number 2, bla bla bla\n"+
            "Answer to question number 2, bla bla bla, Answer to question number 2, bla bla bla, Answer to question number 2, bla bla bla"},


            {"Answer to questions number 3,Answer to questions number 3,Answer to questions number 3,\n"+
            "Answer to questions number 3,Answer to questions number 3,Answer to questions number 3,Answer to questions number 3,"}
    };


    public ExpandableTextViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return faqs.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return answer[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return faqs[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return answer[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String questionFaq = (String)getGroup(groupPosition);
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.faq_title, null);

        }

        TextView questionFaq2=convertView.findViewById(R. id.faqTitleView);
        questionFaq2.setTypeface(null, Typeface.BOLD);
        questionFaq2.setText(questionFaq);
        return convertView;
    }



    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String answerFaq = (String) getChild(groupPosition, childPosition);
        if (convertView == null){
            LayoutInflater inflater=(LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.faq_answer, null);
        }
        TextView answerFaq2 = convertView.findViewById(R. id.descriptionFaqView);
        answerFaq2.setText(answerFaq);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
