/*
   Copyright 2012-2022 Michael Pozhidaev <msp@luwrain.org>

   This file is part of LUWRAIN.

   LUWRAIN is free software; you can redistribute it and/or
   modify it under the terms of the GNU General Public
   License as published by the Free Software Foundation; either
   version 3 of the License, or (at your option) any later version.

   LUWRAIN is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
   General Public License for more details.
*/

package org.luwrain.controls;

import java.util.*;

import org.luwrain.core.*;
import org.luwrain.nlp.*;
import static org.luwrain.controls.EditUtils.*;

public class EditSpellChecking implements EditArea.ChangeListener
{
    private final Luwrain luwrain;
    private final SpellChecker checker = new SpellCheckerFactory().newChecker("ru");
    public EditSpellChecking(Luwrain luwrain )
    {
	NullCheck.notNull(luwrain, "luwrain ");
	this .luwrain = luwrain; 
}

    @Override public void onEditChange(EditArea editArea, MarkedLines lines, HotPoint hotPoint)
    {
	final SortedMap<Integer, String> text = new TreeMap<>();
	blockBounds(editArea, hotPoint.getHotPointY(),(line, marks)->(!line.trim().isEmpty()),
		    (lines_, index)->text.put(index, lines.getLine(index)));
	luwrain.executeBkg(()->check(editArea, text));
    }

    private void check(EditArea editArea, SortedMap<Integer, String> text)
{
	final List<String> textLines = new ArrayList<>();
	for(Map.Entry<Integer, String> e: text.entrySet())
	    textLines.add(e.getValue());
	final SpellText spellText = new SpellText(textLines.toArray(new String[textLines.size()]), checker);
	final List<List<LineMarks.Mark>> marks = spellText.buildMarks();
	luwrain.runUiSafely(()->setResult(editArea, text, marks));
}

    private void setResult(EditArea editArea, SortedMap<Integer, String> text, List<List<LineMarks.Mark>> marks)
    {
	editArea.update((lines, hotPoint)->{
		int index = 0;
		for(Map.Entry<Integer, String> e: text.entrySet())
		{
		    final int lineIndex = e.getKey().intValue();
		    lines.setLineMarks(lineIndex, new DefaultLineMarks.Builder(lines.getLineMarks(lineIndex)).addAll(marks.get(index)).build());
		    index++;
		}
		return false;
	    });
    }
}