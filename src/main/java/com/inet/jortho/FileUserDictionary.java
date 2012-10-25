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
 *  Created on 23.12.2007
 */
package com.inet.jortho;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Locale;

/**
 * This is a reference implementation of the interface {@link UserDictionaryProvider}. It save the user dictionaries on the local disk as text files.
 *
 * @author Volker Berlin
 */
public class FileUserDictionary implements UserDictionaryProvider {

    public static final int NAME_BUFFER_SIZE = 4096;
    private File file;
    private final String fileBase;

    /**
     * Create a FileUserDictionary with the dictionaries in the root of the current application.
     */
    public FileUserDictionary() {
        this("");
    }

    /**
     * Create a FileUserDictionary with the dictionaries on a specific location.
     *
     * @param fileBase the base
     */
    public FileUserDictionary(String fileBase) {
        if (fileBase == null) {
            fileBase = "";
        }
        fileBase = fileBase.trim();
        fileBase = fileBase.replace('\\', '/');
        if (fileBase.length() > 0 && !fileBase.endsWith("/")) {
            fileBase += "/";
        }
        this.fileBase = fileBase;
    }

    /**
     * {@inheritDoc}
     */
    public void addWord(final String word) {
        try {
            final FileOutputStream output = new FileOutputStream(file, true);
            final Writer writer = new OutputStreamWriter(output, "UTF8");
            if (file.length() > 0) {
                writer.write("\n");
            }
            writer.write(word);
            writer.close();
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getUserWords(final Locale locale) {
        file = new File(fileBase + "UserDictionary_" + locale + ".txt");
        try {
            final FileInputStream input = new FileInputStream(file);
            final Reader reader = new InputStreamReader(input, "UTF8");
            final StringBuilder builder = new StringBuilder();
            final char[] buffer = new char[NAME_BUFFER_SIZE];
            int count;
            while ((count = reader.read(buffer)) > 0) {
                builder.append(buffer, 0, count);
            }
            reader.close();
            return builder.toString();
        } catch (final IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void setUserWords(final String wordList) {
        try {
            final FileOutputStream output = new FileOutputStream(file);
            final Writer writer = new OutputStreamWriter(output, "UTF8");
            writer.write(wordList);
            writer.close();
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
}
