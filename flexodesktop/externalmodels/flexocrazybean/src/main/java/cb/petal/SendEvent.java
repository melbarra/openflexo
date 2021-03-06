/*
 * (c) Copyright 2010-2011 AgileBirds
 *
 * This file is part of OpenFlexo.
 *
 * OpenFlexo is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenFlexo is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenFlexo. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package cb.petal;
import java.util.Collection;

/**
 * Represents sendEvent object
 *
 * @version $Id: SendEvent.java,v 1.3 2011/09/12 11:46:49 gpolet Exp $
 * @author  <A HREF="mailto:markus.dahm@berlin.de">M. Dahm</A>
 */
public class SendEvent extends QuidObject {
  public SendEvent(PetalNode parent, Collection params) {
    super(parent, "sendEvent", params);
  }

  public SendEvent() {
    super("sendEvent");
  }

  public Event getEvent() {
    return (Event)getProperty("Event");
  }

  public void setEvent(Event o) {
    defineProperty("Event", o);
  }

  public String getTarget() {
    return getPropertyAsString("target");
  }

  public void setTarget(String o) {
    defineProperty("target", o);
  }

  public ActionTime getActionTime() {
    return (ActionTime)getProperty("ActionTime");
  }

  public void setActionTime(ActionTime o) {
    defineProperty("ActionTime", o);
  }

  @Override
public void accept(Visitor v) {
    v.visit(this);
  }
}
