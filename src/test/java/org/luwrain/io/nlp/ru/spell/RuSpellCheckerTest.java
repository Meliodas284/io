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


package org.luwrain.nlp.ru.spell;

//https://languagetool.org/development/api/org/languagetool/rules/spelling/SpellingCheckRule.html


import java.io.*;
import java.net.*;

import org.junit.*;

import org.luwrain.core.*;

public class RuSpellCheckerTest extends Assert
{
    private RuSpellChecker c = null;

    @Test public void main()
    {
    }

    @Before public void  create()
    {
	c = new RuSpellChecker();
    }
}