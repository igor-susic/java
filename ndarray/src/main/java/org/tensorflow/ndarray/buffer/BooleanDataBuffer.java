/*
 Copyright 2019 The TensorFlow Authors. All Rights Reserved.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 =======================================================================
 */
package org.tensorflow.ndarray.buffer;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ReadOnlyBufferException;

/**
 * A {@link DataBuffer} of booleans.
 */
public interface BooleanDataBuffer extends DataBuffer<Boolean> {

  /**
   * Reads the boolean at the given index.
   *
   * @param index the index from which the float will be read
   * @return the boolean at the given index
   * @throws IndexOutOfBoundsException if index is negative or not smaller than the buffer size
   */
  boolean getBoolean(long index);

  /**
   * Writes the given boolean into this buffer at the given index.
   *
   * @param value the boolean to be written
   * @param index the index at which the value will be written
   * @return this buffer
   * @throws IndexOutOfBoundsException if index is negative or not smaller than the buffer size
   * @throws ReadOnlyBufferException if this buffer is read-only
   */
  BooleanDataBuffer setBoolean(boolean value, long index);

  /**
   * Bulk <i>get</i> method, using boolean arrays.
   * <p>
   * This method transfers values from this buffer into the given destination array. If there are
   * fewer values in the buffer than are required to satisfy the request, that is, if
   * {@code dst.length > size()}, then no values are transferred and a
   * BufferUnderflowException is thrown.
   * <p>
   * Otherwise, this method copies {@code n = dst.length} values from this buffer into the given
   * array.
   *
   * @param dst the array into which values are to be written
   * @return this buffer
   * @throws BufferUnderflowException if there are not enough values to copy from this buffer
   */
  default BooleanDataBuffer read(boolean[] dst) {
    return read(dst, 0, dst.length);
  }

  /**
   * Bulk <i>get</i> method, using boolean arrays.
   * <p>
   * This method transfers values from this buffer into the given destination array. If there are
   * fewer values in the buffer than are required to satisfy the request, that is, if
   * {@code length > size()}, then no values are transferred and a
   * BufferUnderflowException is thrown.
   * <p>
   * Otherwise, this method copies {@code n = length} values from this buffer into the given array
   * starting at the given offset.
   *
   * @param dst the array into which values are to be written
   * @param offset the offset within the array of the first value to be written; must be
   *               non-negative and no larger than {@code dst.length}
   * @param length the maximum number of values to be written to the given array; must be
   *               non-negative and no larger than {@code dst.length - offset}
   * @return this buffer
   * @throws BufferUnderflowException if there are fewer than length values remaining in this buffer
   * @throws IndexOutOfBoundsException if the preconditions on the offset and length parameters do
   *                                   not hold
   */
  BooleanDataBuffer read(boolean[] dst, int offset, int length);

  /**
   * Bulk <i>put</i> method, using boolean arrays.
   * <p>
   * This method transfers the values in the given source array into this buffer. If there are 
   * more values in the source array than in this buffer, that is, if
   * {@code src.length > size()}, then no values are transferred and a
   * BufferOverflowException is thrown.
   * <p>
   * Otherwise, this method copies {@code n = src.length} values from the given array.
   *
   * @param src the source array from which values are to be read
   * @return this buffer
   * @throws BufferOverflowException if there is insufficient space in this buffer for the values in
   *                                 the source array
   * @throws ReadOnlyBufferException if this buffer is read-only
   */
  default BooleanDataBuffer write(boolean[] src) {
    return write(src, 0, src.length);
  }
  
  /**
   * Bulk <i>put</i> method, using boolean arrays.
   * <p>
   * This method transfers the values in the given source array into this buffer. If there are 
   * more values in the source array than in this buffer, that is, if
   * {@code length > size()}, then no values are transferred and a
   * BufferOverflowException is thrown.
   * <p>
   * Otherwise, this method copies {@code n = length} values from the given array into this buffer,
   * starting at the given offset.
   *
   * @param src the source array from which values are to be read
   * @param offset the offset within the array of the first value to be read; must be non-negative
   *               and no larger than {@code src.length}
   * @param length the number of values to be read from the given array; must be non-negative and no
   *               larger than {@code src.length - offset}
   * @return this buffer
   * @throws BufferOverflowException if there is insufficient space in this buffer for the values in
   *                                 the source array
   * @throws IndexOutOfBoundsException if the preconditions on the offset and length parameters do
   *                                   not hold
   * @throws ReadOnlyBufferException if this buffer is read-only
   */
  BooleanDataBuffer write(boolean[] src, int offset, int length);

  @Override
  default Boolean getObject(long index) {
    return getBoolean(index);
  }

  @Override
  default BooleanDataBuffer setObject(Boolean value, long index) {
    return setBoolean(value, index);
  }

  @Override
  BooleanDataBuffer copyTo(DataBuffer<Boolean> dst, long size);

  @Override
  default BooleanDataBuffer offset(long index) {
    return slice(index, size() - index);
  }

  @Override
  default BooleanDataBuffer narrow(long size) {
    return slice(0, size);
  }

  @Override
  BooleanDataBuffer slice(long index, long size);

  @Override
  default DataBufferWindow<BooleanDataBuffer> window(long size) {
    throw new UnsupportedOperationException();
  }
}
