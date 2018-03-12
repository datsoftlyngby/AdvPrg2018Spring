/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dapi.animation.impl;

import java.util.ArrayList;
import app2dapi.animation.Notifiable;
import app2dapi.animation.NotifiableList;

/**
 *
 * @author Tobias Grundtvig
 */
public class NotificationListImpl implements NotifiableList, Notifiable
{
    private final ArrayList<Notifiable> list;

    public NotificationListImpl()
    {
        list = new ArrayList<>();
    }
    
    @Override
    public synchronized void addNotifiable(Notifiable notifiable)
    {
        list.add(notifiable);
    }

    @Override
    public synchronized void removeNotifiable(Notifiable notifiable)
    {
        list.remove(notifiable);
    }

    @Override
    public synchronized void onNotify()
    {
        for(Notifiable notifiable : list)
        {
            notifiable.onNotify();
        }
    }
}
