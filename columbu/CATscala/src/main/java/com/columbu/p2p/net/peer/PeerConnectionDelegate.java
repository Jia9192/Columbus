package com.galileo.p2p.net.peer;

import com.galileo.core.overlay.message.Message;
import com.galileo.utils.Sha256Hash;
import com.galileo.p2p.net.message.TronMessage;

public abstract class PeerConnectionDelegate {

  public abstract void onMessage(PeerConnection peer, TronMessage msg);

  public abstract Message getMessage(Sha256Hash msgId);

  public abstract void onConnectPeer(PeerConnection peer);

  public abstract void onDisconnectPeer(PeerConnection peer);

}
