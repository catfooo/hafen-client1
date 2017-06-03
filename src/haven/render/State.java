/*
 *  This file is part of the Haven & Hearth game client.
 *  Copyright (C) 2009 Fredrik Tolf <fredrik@dolda2000.com>, and
 *                     Björn Johannessen <johannessen.bjorn@gmail.com>
 *
 *  Redistribution and/or modification of this file is subject to the
 *  terms of the GNU Lesser General Public License, version 3, as
 *  published by the Free Software Foundation.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  Other parts of this source tree adhere to other copying
 *  rights. Please see the file `COPYING' in the root directory of the
 *  source tree for details.
 *
 *  A copy the GNU Lesser General Public License is distributed along
 *  with the source tree of which this file is a part in the file
 *  `doc/LPGL-3'. If it is missing for any reason, please see the Free
 *  Software Foundation's website at <http://www.fsf.org/>, or write
 *  to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 *  Boston, MA 02111-1307 USA
 */

package haven.render;

import java.util.*;
import haven.render.sl.*;

public class State {
    public static class Slot<T extends State> {
	static Slots slots = new Slots(new Slot<?>[0]);
	public final Type type;
	public final int id;
	public final Class<T> scl;
	private int depid = -1;

	public enum Type {
	    SYS, GEOM, DRAW
	}

	public static class Slots {
	    public final Slot<?>[] idlist;

	    public Slots(Slot<?>[] idlist) {
		this.idlist = idlist;
	    }
	}

	public Slot(Type type, Class<T> scl) {
	    this.type = type;
	    this.scl = scl;
	    synchronized(Slot.class) {
		this.id = slots.idlist.length;
		Slot<?>[] nlist = Arrays.copyOf(slots.idlist, this.id + 1);
		nlist[this.id] = this;
		slots = new Slots(nlist);
	    }
	}
    }

    public ShaderMacro shader() {
	return(null);
    }
}
