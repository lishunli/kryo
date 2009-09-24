
package serializers;

import java.io.IOException;
import java.util.ArrayList;

import serializers.java.Image;
import serializers.java.Media;
import serializers.java.MediaContent;
import serializers.java.Image.Size;
import serializers.java.Media.Player;
import serializers.scala.SbinarySerializerSupport;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.ObjectBuffer;

public class KryoSerializer extends StdMediaSerializer {
	protected Kryo kryo;
	protected ObjectBuffer objectBuffer;

	public KryoSerializer () {
		this("persistence");
	}

	public KryoSerializer (String name) {
		super(name);
		kryo = new Kryo();
		kryo.register(ArrayList.class);
		kryo.register(MediaContent.class);
		kryo.register(Media.class);
		kryo.register(Media.Player.class);
		kryo.register(Image.class);
		kryo.register(Image.Size.class);
		objectBuffer = new ObjectBuffer(kryo);
	}

	public MediaContent deserialize (byte[] array) throws Exception {
		return (MediaContent)objectBuffer.toObject(array);
	}

	public byte[] serialize (MediaContent content) throws Exception {
		return objectBuffer.toBytes(content);
	}
}
