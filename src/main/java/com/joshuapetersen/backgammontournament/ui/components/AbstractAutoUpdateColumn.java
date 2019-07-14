package com.joshuapetersen.backgammontournament.ui.components;

import javafx.scene.control.TableColumn;

public abstract class AbstractAutoUpdateColumn<S,T> extends TableColumn<S,T>
{
        public AbstractAutoUpdateColumn()
        {
            super();
        }

        protected void setup()
        {

            setOnEditStart(this::startEdit);
            setOnEditCancel(this::stopEdit);
            setOnEditCommit(this::commitEdit);
            setEditable(true);
        }

        protected abstract void startEdit(CellEditEvent<S,T> event);
        protected abstract void stopEdit(CellEditEvent<S,T> event);
        protected abstract void commitEdit(CellEditEvent<S,T> event);


        public AbstractAutoUpdateColumn(String text)
        {
            super(text);

        }

}
