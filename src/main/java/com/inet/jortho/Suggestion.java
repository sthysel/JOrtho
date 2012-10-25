/*
 *  JOrtho
 *
 *  Copyright (C) 2005-2008 by i-net software
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License as 
 *  published by the Free Software Foundation; either version 2 of the
 *  License, or (at your option) any later version. 
 *
 *  This program is distributed in the hope that it will be useful, but
 *  WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *  General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 *  USA.
 *  
 *  Created on 04.11.2005
 */
package com.inet.jortho;

/**
 * This class hold one Suggestion for another spelling. Note: this class has a natural ordering that is inconsistent with equals.
 *
 * @author Volker Berlin
 */
final class Suggestion implements Comparable<Suggestion> {

    private final int diff;
    private final String word;

    /**
     * Construct a suggestion
     *
     * @param word the characters of the suggested the word.
     * @param diff the difference to the original word.
     */
    Suggestion(final CharSequence word, final int diff) {
        this.word = word.toString();
        this.diff = diff;
    }

    /**
     * {@inheritDoc}
     */
    public int compareTo(final Suggestion sugg) {
        return diff - sugg.diff;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object sugg) {
        if (sugg instanceof Suggestion) {
            return word.equals(((Suggestion) sugg).word);
        }
        return false;
    }

    /**
     * Return a value that describes dissimilarity to the original word. A value of 0 means that the value is 100% identical. This should not occur.
     *
     * @return the dissimilarity, so larger to differ the word.
     */
    public int getDissimilarity() {
        return diff;
    }

    /**
     * Get the suggested word.
     *
     * @return the word
     */
    public String getWord() {
        return word;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return word.hashCode();
    }

    /**
     * Return the suggested word and it equals to getWord().
     */
    @Override
    public String toString() {
        return word;
    }
}
