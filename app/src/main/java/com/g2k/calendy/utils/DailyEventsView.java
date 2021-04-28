package com.g2k.calendy.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Constraints;

/**
 * a constraint layout subclass to a show daily event
 * @author Yiğit Yalın
 * @version 2021/04/26
 */
public class DailyEventsView extends ConstraintLayout {
    private final int WIDTH = LayoutParams.MATCH_PARENT;
    private final int HEIGHT = 250;

    private final int HEADER_START = 40;
    private final int HEADER_TOP = 25;
    private final int HEADER_END = 10;
    private final int HEADER_BOTTOM = 0;

    private final int TIME_START = 940;
    private final int TIME_TOP = 25;
    private final int TIME_END = 10;
    private final int TIME_BOTTOM = 0;

    private final int DESCRIPTION_START = 40;
    private final int DESCRIPTION_TOP = 90;
    private final int DESCRIPTION_END = 10;
    private final int DESCRIPTION_BOTTOM = 0;

    private final int EVENT_COLOR = Color.RED;

    private TextView eventHeader;
    private TextView eventTime;
    private TextView eventDescription;
    private ConstraintLayout.LayoutParams layoutParams;

    public DailyEventsView(@NonNull Context context,
                           @NonNull String eventHeaderText,
                           @NonNull String eventTimeText,
                           String eventDescriptionText) {
        super(context);

        layoutParams = new ConstraintLayout.LayoutParams(WIDTH, HEIGHT);
        layoutParams.width = WIDTH;
        layoutParams.height = HEIGHT;
        this.setLayoutParams(layoutParams);

        eventHeader = new TextView(context);
        eventTime = new TextView(context);
        eventDescription = new TextView(context);

        eventHeader.setText(eventHeaderText);
        eventTime.setText(eventTimeText);
        eventDescription.setText(eventDescriptionText);

        eventHeader.setTextColor(EVENT_COLOR);
        eventTime.setTextColor(EVENT_COLOR);
        eventDescription.setTextColor(Color.BLACK);

        eventHeader.setPaddingRelative(HEADER_START, HEADER_TOP, HEADER_END, HEADER_BOTTOM);
        eventTime.setPaddingRelative(TIME_START, TIME_TOP, TIME_END, TIME_BOTTOM);
        eventDescription.setPaddingRelative(DESCRIPTION_START, DESCRIPTION_TOP, DESCRIPTION_END, DESCRIPTION_BOTTOM);

        this.addView(eventHeader);
        this.addView(eventTime);
        this.addView(eventDescription);
    }
}
