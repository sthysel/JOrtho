package com.inet.jorthotests;

import com.inet.jortho.SpellChecker;
import java.net.MalformedURLException;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import junit.framework.Assert;

import org.junit.Test;

public class EventTest {

    static {
        try {
            AllTests.init();
        } catch (MalformedURLException ex) {
            System.out.println(ex);
        }
    }

    /**
     * Compare 2 JRadioButtonMenuItem
     */
    private void assertEquals(final String description, final JRadioButtonMenuItem item1, final JRadioButtonMenuItem item2) {
        Assert.assertEquals(description + ": Name", item1.getName(), item2.getName());
        Assert.assertEquals(description + ": Selected", item1.isSelected(), item2.isSelected());
    }

    @Test
    public void testChangeLanguage() throws Exception {
        final JMenu menu1 = SpellChecker.createLanguagesMenu();
        final JMenu menu2 = SpellChecker.createLanguagesMenu();
        Assert.assertEquals("Menucount", menu1.getItemCount(), menu2.getItemCount());
        Assert.assertTrue("2 languages required:" + menu1.getItemCount(), menu1.getItemCount() >= 2);
        final JRadioButtonMenuItem item1_1 = (JRadioButtonMenuItem) menu1.getItem(0);
        final JRadioButtonMenuItem item1_2 = (JRadioButtonMenuItem) menu1.getItem(1);
        final JRadioButtonMenuItem item2_1 = (JRadioButtonMenuItem) menu2.getItem(0);
        final JRadioButtonMenuItem item2_2 = (JRadioButtonMenuItem) menu2.getItem(1);
        assertEquals("Item 1", item1_1, item2_1);
        assertEquals("Item 2", item1_2, item2_2);
        //Change the selected language
        JRadioButtonMenuItem notSelected = item1_1.isSelected() ? item1_2 : item1_1;
        JRadioButtonMenuItem selected = item1_1.isSelected() ? item1_1 : item1_2;
        Assert.assertFalse("Selected", notSelected.isSelected());
        //Assert.assertTrue("Selected", selected.isSelected());
        notSelected.doClick(0);
        Assert.assertTrue("Selected", notSelected.isSelected());
        Assert.assertFalse("Selected", selected.isSelected());
        assertEquals("Item 1", item1_1, item2_1);
        assertEquals("Item 2", item1_2, item2_2);
        Thread.sleep(10); // for loading thread
        notSelected = item2_1.isSelected() ? item2_2 : item2_1;
        selected = item2_1.isSelected() ? item2_1 : item2_2;
        Assert.assertFalse("Selected", notSelected.isSelected());
        Assert.assertTrue("Selected", selected.isSelected());
        notSelected.doClick(0);
        Assert.assertTrue("Selected", notSelected.isSelected());
        Assert.assertFalse("Selected", selected.isSelected());
        assertEquals("Item 1", item1_1, item2_1);
        assertEquals("Item 2", item1_2, item2_2);
        Thread.sleep(10); // for loading thread
    }
}
