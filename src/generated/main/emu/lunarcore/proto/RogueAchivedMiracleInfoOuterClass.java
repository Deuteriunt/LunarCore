// Code generated by protocol buffer compiler. Do not edit!
package emu.lunarcore.proto;

import java.io.IOException;
import us.hebi.quickbuf.FieldName;
import us.hebi.quickbuf.InvalidProtocolBufferException;
import us.hebi.quickbuf.JsonSink;
import us.hebi.quickbuf.JsonSource;
import us.hebi.quickbuf.MessageFactory;
import us.hebi.quickbuf.ProtoMessage;
import us.hebi.quickbuf.ProtoSink;
import us.hebi.quickbuf.ProtoSource;
import us.hebi.quickbuf.RepeatedMessage;

public final class RogueAchivedMiracleInfoOuterClass {
  /**
   * Protobuf type {@code RogueAchivedMiracleInfo}
   */
  public static final class RogueAchivedMiracleInfo extends ProtoMessage<RogueAchivedMiracleInfo> implements Cloneable {
    private static final long serialVersionUID = 0L;

    /**
     * <code>repeated .RogueMiracle rogue_miracle_list = 13;</code>
     */
    private final RepeatedMessage<RogueMiracleOuterClass.RogueMiracle> rogueMiracleList = RepeatedMessage.newEmptyInstance(RogueMiracleOuterClass.RogueMiracle.getFactory());

    private RogueAchivedMiracleInfo() {
    }

    /**
     * @return a new empty instance of {@code RogueAchivedMiracleInfo}
     */
    public static RogueAchivedMiracleInfo newInstance() {
      return new RogueAchivedMiracleInfo();
    }

    /**
     * <code>repeated .RogueMiracle rogue_miracle_list = 13;</code>
     * @return whether the rogueMiracleList field is set
     */
    public boolean hasRogueMiracleList() {
      return (bitField0_ & 0x00000001) != 0;
    }

    /**
     * <code>repeated .RogueMiracle rogue_miracle_list = 13;</code>
     * @return this
     */
    public RogueAchivedMiracleInfo clearRogueMiracleList() {
      bitField0_ &= ~0x00000001;
      rogueMiracleList.clear();
      return this;
    }

    /**
     * <code>repeated .RogueMiracle rogue_miracle_list = 13;</code>
     *
     * This method returns the internal storage object without modifying any has state.
     * The returned object should not be modified and be treated as read-only.
     *
     * Use {@link #getMutableRogueMiracleList()} if you want to modify it.
     *
     * @return internal storage object for reading
     */
    public RepeatedMessage<RogueMiracleOuterClass.RogueMiracle> getRogueMiracleList() {
      return rogueMiracleList;
    }

    /**
     * <code>repeated .RogueMiracle rogue_miracle_list = 13;</code>
     *
     * This method returns the internal storage object and sets the corresponding
     * has state. The returned object will become part of this message and its
     * contents may be modified as long as the has state is not cleared.
     *
     * @return internal storage object for modifications
     */
    public RepeatedMessage<RogueMiracleOuterClass.RogueMiracle> getMutableRogueMiracleList() {
      bitField0_ |= 0x00000001;
      return rogueMiracleList;
    }

    /**
     * <code>repeated .RogueMiracle rogue_miracle_list = 13;</code>
     * @param value the rogueMiracleList to add
     * @return this
     */
    public RogueAchivedMiracleInfo addRogueMiracleList(
        final RogueMiracleOuterClass.RogueMiracle value) {
      bitField0_ |= 0x00000001;
      rogueMiracleList.add(value);
      return this;
    }

    /**
     * <code>repeated .RogueMiracle rogue_miracle_list = 13;</code>
     * @param values the rogueMiracleList to add
     * @return this
     */
    public RogueAchivedMiracleInfo addAllRogueMiracleList(
        final RogueMiracleOuterClass.RogueMiracle... values) {
      bitField0_ |= 0x00000001;
      rogueMiracleList.addAll(values);
      return this;
    }

    @Override
    public RogueAchivedMiracleInfo copyFrom(final RogueAchivedMiracleInfo other) {
      cachedSize = other.cachedSize;
      if ((bitField0_ | other.bitField0_) != 0) {
        bitField0_ = other.bitField0_;
        rogueMiracleList.copyFrom(other.rogueMiracleList);
      }
      return this;
    }

    @Override
    public RogueAchivedMiracleInfo mergeFrom(final RogueAchivedMiracleInfo other) {
      if (other.isEmpty()) {
        return this;
      }
      cachedSize = -1;
      if (other.hasRogueMiracleList()) {
        getMutableRogueMiracleList().addAll(other.rogueMiracleList);
      }
      return this;
    }

    @Override
    public RogueAchivedMiracleInfo clear() {
      if (isEmpty()) {
        return this;
      }
      cachedSize = -1;
      bitField0_ = 0;
      rogueMiracleList.clear();
      return this;
    }

    @Override
    public RogueAchivedMiracleInfo clearQuick() {
      if (isEmpty()) {
        return this;
      }
      cachedSize = -1;
      bitField0_ = 0;
      rogueMiracleList.clearQuick();
      return this;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (!(o instanceof RogueAchivedMiracleInfo)) {
        return false;
      }
      RogueAchivedMiracleInfo other = (RogueAchivedMiracleInfo) o;
      return bitField0_ == other.bitField0_
        && (!hasRogueMiracleList() || rogueMiracleList.equals(other.rogueMiracleList));
    }

    @Override
    public void writeTo(final ProtoSink output) throws IOException {
      if ((bitField0_ & 0x00000001) != 0) {
        for (int i = 0; i < rogueMiracleList.length(); i++) {
          output.writeRawByte((byte) 106);
          output.writeMessageNoTag(rogueMiracleList.get(i));
        }
      }
    }

    @Override
    protected int computeSerializedSize() {
      int size = 0;
      if ((bitField0_ & 0x00000001) != 0) {
        size += (1 * rogueMiracleList.length()) + ProtoSink.computeRepeatedMessageSizeNoTag(rogueMiracleList);
      }
      return size;
    }

    @Override
    @SuppressWarnings("fallthrough")
    public RogueAchivedMiracleInfo mergeFrom(final ProtoSource input) throws IOException {
      // Enabled Fall-Through Optimization (QuickBuffers)
      int tag = input.readTag();
      while (true) {
        switch (tag) {
          case 106: {
            // rogueMiracleList
            tag = input.readRepeatedMessage(rogueMiracleList, tag);
            bitField0_ |= 0x00000001;
            if (tag != 0) {
              break;
            }
          }
          case 0: {
            return this;
          }
          default: {
            if (!input.skipField(tag)) {
              return this;
            }
            tag = input.readTag();
            break;
          }
        }
      }
    }

    @Override
    public void writeTo(final JsonSink output) throws IOException {
      output.beginObject();
      if ((bitField0_ & 0x00000001) != 0) {
        output.writeRepeatedMessage(FieldNames.rogueMiracleList, rogueMiracleList);
      }
      output.endObject();
    }

    @Override
    public RogueAchivedMiracleInfo mergeFrom(final JsonSource input) throws IOException {
      if (!input.beginObject()) {
        return this;
      }
      while (!input.isAtEnd()) {
        switch (input.readFieldHash()) {
          case -452426123:
          case 1925521905: {
            if (input.isAtField(FieldNames.rogueMiracleList)) {
              if (!input.trySkipNullValue()) {
                input.readRepeatedMessage(rogueMiracleList);
                bitField0_ |= 0x00000001;
              }
            } else {
              input.skipUnknownField();
            }
            break;
          }
          default: {
            input.skipUnknownField();
            break;
          }
        }
      }
      input.endObject();
      return this;
    }

    @Override
    public RogueAchivedMiracleInfo clone() {
      return new RogueAchivedMiracleInfo().copyFrom(this);
    }

    @Override
    public boolean isEmpty() {
      return ((bitField0_) == 0);
    }

    public static RogueAchivedMiracleInfo parseFrom(final byte[] data) throws
        InvalidProtocolBufferException {
      return ProtoMessage.mergeFrom(new RogueAchivedMiracleInfo(), data).checkInitialized();
    }

    public static RogueAchivedMiracleInfo parseFrom(final ProtoSource input) throws IOException {
      return ProtoMessage.mergeFrom(new RogueAchivedMiracleInfo(), input).checkInitialized();
    }

    public static RogueAchivedMiracleInfo parseFrom(final JsonSource input) throws IOException {
      return ProtoMessage.mergeFrom(new RogueAchivedMiracleInfo(), input).checkInitialized();
    }

    /**
     * @return factory for creating RogueAchivedMiracleInfo messages
     */
    public static MessageFactory<RogueAchivedMiracleInfo> getFactory() {
      return RogueAchivedMiracleInfoFactory.INSTANCE;
    }

    private enum RogueAchivedMiracleInfoFactory implements MessageFactory<RogueAchivedMiracleInfo> {
      INSTANCE;

      @Override
      public RogueAchivedMiracleInfo create() {
        return RogueAchivedMiracleInfo.newInstance();
      }
    }

    /**
     * Contains name constants used for serializing JSON
     */
    static class FieldNames {
      static final FieldName rogueMiracleList = FieldName.forField("rogueMiracleList", "rogue_miracle_list");
    }
  }
}