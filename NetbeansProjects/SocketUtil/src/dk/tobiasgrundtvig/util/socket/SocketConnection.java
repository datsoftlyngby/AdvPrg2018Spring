/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.tobiasgrundtvig.util.socket;

import java.io.Closeable;
import java.io.DataInput;
import java.io.DataOutput;

/**
 *
 * @author Tobias
 */
public interface SocketConnection extends DataInput, DataOutput, Closeable
{
}
