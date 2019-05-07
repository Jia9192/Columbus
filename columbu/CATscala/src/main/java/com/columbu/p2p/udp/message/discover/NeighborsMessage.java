package com.galileo.p2p.udp.message.discover;

import static com.galileo.p2p.udp.message.UdpMessageTypeEnum.DISCOVER_NEIGHBORS;

import com.google.protobuf.ByteString;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import com.galileo.p2p.udp.message.Message;
import com.galileo.core.overlay.discover.node.Node;
import com.galileo.utils.ByteArray;
import com.galileo.protos.protos.Discover;
import com.galileo.protos.protos.Discover.Endpoint;
import com.galileo.protos.protos.Discover.Neighbours;
import com.galileo.protos.protos.Discover.Neighbours.Builder;

@Slf4j
public class NeighborsMessage extends Message {

  private Discover.Neighbours neighbours;

  public NeighborsMessage(byte[] data) throws Exception{
    super(DISCOVER_NEIGHBORS, data);
    this.neighbours = Discover.Neighbours.parseFrom(data);
  }

  public NeighborsMessage(Node from, List<Node> neighbours) {
    super(DISCOVER_NEIGHBORS, null);
    Builder builder = Neighbours.newBuilder()
        .setTimestamp(System.currentTimeMillis());

    neighbours.forEach(neighbour -> {
      Endpoint endpoint = Endpoint.newBuilder()
          .setAddress(ByteString.copyFrom(ByteArray.fromString(neighbour.getHost())))
          .setPort(neighbour.getPort())
          .setNodeId(ByteString.copyFrom(neighbour.getId()))
          .build();

      builder.addNeighbours(endpoint);
    });

    Endpoint fromEndpoint = Endpoint.newBuilder()
        .setAddress(ByteString.copyFrom(ByteArray.fromString(from.getHost())))
        .setPort(from.getPort())
        .setNodeId(ByteString.copyFrom(from.getId()))
        .build();

    builder.setFrom(fromEndpoint);

    this.neighbours = builder.build();

    this.data = this.neighbours.toByteArray();
  }

  public List<Node> getNodes() {
    List<Node> nodes = new ArrayList<>();
    neighbours.getNeighboursList().forEach(neighbour -> nodes.add(
        new Node(neighbour.getNodeId().toByteArray(),
            ByteArray.toStr(neighbour.getAddress().toByteArray()),
            neighbour.getPort())));
    return nodes;
  }

  @Override
  public Node getFrom() {
    return Message.getNode(neighbours.getFrom());
  }

  @Override
  public String toString() {
    return "[neighbours: " + neighbours;
  }

}
