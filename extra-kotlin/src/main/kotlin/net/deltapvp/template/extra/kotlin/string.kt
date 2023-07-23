/*
 * This file is part of Template, licensed under the MIT License.
 *
 * Copyright (c) 2023 powercas_gamer
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.deltapvp.template.extra.kotlin

/**
 * Returns `true` if a substring of this char sequence starting at the specified offset [startIndex] starts with any of the specified prefixes.
 */
fun CharSequence.startsWithAny(
    vararg prefixes: CharSequence,
    startIndex: Int = 0,
    ignoreCase: Boolean = false
): Boolean {
    return prefixes.any { this.startsWith(it, startIndex, ignoreCase) }
}

/**
 * Returns `true` if this char sequence ends with any of the specified suffixes.
 */
fun CharSequence.endsWithAny(
    vararg suffixes: CharSequence,
    ignoreCase: Boolean = false
): Boolean {
    return suffixes.any { this.endsWith(it, ignoreCase) }
}
