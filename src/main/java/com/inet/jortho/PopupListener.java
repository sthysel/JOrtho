/*
 *  JOrtho
 *
 *  Copyright (C) 2005-2008 by i-net software
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License as 
 *  published by the Free Software Foundation; either version 2 of the
 *  License, or (at your option) any later version. 
 *
 *  This program is distributed in the hope that it will be useful, but
 *  WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *  General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 *  USA.
 *  
 *  Created on 06.12.2007
 */
package com.inet.jortho;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;

/**
 * Implement a MouseListener for Pop up Event. It simple show the pop up if there are the right mouse event. This should be part of the standard Java API.
 *
 * @author Volker Berlin
 */
public class PopupListener extends MouseAdapter {

    private final JPopupMenu menu;

    public PopupListener(final JPopupMenu menu) {
        this.menu = menu;
    }

    private void maybeShowPopup(final MouseEvent ev) {
        if (ev.isPopupTrigger()) {
            menu.show(ev.getComponent(), ev.getX(), ev.getY());
        }
    }

    @Override
    public void mousePressed(final MouseEvent ev) {
        maybeShowPopup(ev);
    }

    @Override
    public void mouseReleased(final MouseEvent ev) {
        maybeShowPopup(ev);
    }
}
