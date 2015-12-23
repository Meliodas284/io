/*
   Copyright 2012-2015 Michael Pozhidaev <michael.pozhidaev@gmail.com>

   This file is part of the LUWRAIN.

   LUWRAIN is free software; you can redistribute it and/or
   modify it under the terms of the GNU General Public
   License as published by the Free Software Foundation; either
   version 3 of the License, or (at your option) any later version.

   LUWRAIN is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
   General Public License for more details.
*/

package org.luwrain.network ;

import org.luwrain.core.*;

public class Network
{
    public interface ConnectionListener
    {
	void onConnectionProgressLine(String line);
    }

    private Wifi wifi;

    Network(Luwrain luwrain)
    {
	wifi = new Wifi(luwrain);
    }

    //May be called from any thread
    public WifiScanResult wifiScan()
    {
	return wifi.scan();
    }

    public void wifiConnect(WifiNetwork connectTo, ConnectionListener listener)
    {
	wifi.connect(connectTo, listener);
    }
}
