/**
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.lightning.internal.marshaller;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import com.github.lightning.SerializationContext;
import com.github.lightning.base.AbstractMarshaller;
import com.github.lightning.metadata.PropertyDescriptor;

public class DoubleArrayMarshaller extends AbstractMarshaller {

	@Override
	public boolean acceptType(Class<?> type) {
		return double[].class == type || Double[].class == type;
	}

	@Override
	public void marshall(Object value, PropertyDescriptor propertyDescriptor, DataOutput dataOutput, SerializationContext serializationContext)
			throws IOException {

		if (!writePossibleNull(value, dataOutput)) {
			return;
		}

		if (double[].class == propertyDescriptor.getType()) {
			double[] array = (double[]) value;
			dataOutput.writeInt(array.length);

			for (double arrayValue : array) {
				dataOutput.writeDouble(arrayValue);
			}
		}
		else {
			Double[] array = (Double[]) value;
			dataOutput.writeInt(array.length);

			for (double arrayValue : array) {
				dataOutput.writeDouble(arrayValue);
			}
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public <V> V unmarshall(PropertyDescriptor propertyDescriptor, DataInput dataInput, SerializationContext serializationContext) throws IOException {
		if (isNull(dataInput)) {
			return null;
		}

		int size = dataInput.readInt();
		if (double[].class == propertyDescriptor.getType()) {
			double[] array = new double[size];
			for (int i = 0; i < size; i++) {
				array[i] = dataInput.readDouble();
			}

			return (V) array;
		}
		else {
			Double[] array = new Double[size];
			for (int i = 0; i < size; i++) {
				array[i] = dataInput.readDouble();
			}

			return (V) array;
		}
	}
}
