package com.trabauer.twitchtools.gui;

import com.trabauer.twitchtools.gui.vod.download.DownloadStep1;
import com.trabauer.twitchtools.gui.vod.download.DownloadStep2;
import com.trabauer.twitchtools.gui.vod.download.DownloadStep3;
import com.trabauer.twitchtools.gui.vod.download.DownloadStep4;
import com.trabauer.twitchtools.utils.OsUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Flo on 13.11.2014.
 */
public class OldMainFrame extends JFrame {

    private DownloadStep1 downloadStep1Form;
    private DownloadStep2 downloadStep2Form;
    private DownloadStep3 downloadStep3Form;
    private DownloadStep4 downloadStep4Form;

    public final String DOWNLOAD1CARD = "DownloadStep1";
    public final String DOWNLOAD2CARD = "DownloadStep2";
    public final String DOWNLOAD3CARD = "DownloadStep3";
    public final String DOWNLOAD4CARD = "DownloadStep4";


    private JPanel mainFramePanel;
    private JFileChooser fileChooser;



    public OldMainFrame(){
        super("Twitch - Past Broadcast Loader");

        mainFramePanel = (JPanel) getContentPane();
        //mainFramePanel = new JPanel(new CardLayout())
        mainFramePanel.setLayout(new CardLayout(5, 5));


        //mainFramePanel.

//        mainForm = new MainForm(controller);
//        mainForm.addMainFormListener(controller);


        Image icon = null;
        try {
            icon = ImageIO.read(getClass().getResource("twitchTool.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setIconImage(icon);

        downloadStep1Form = new DownloadStep1(icon);
        downloadStep2Form = new DownloadStep2();
        downloadStep3Form = new DownloadStep3();
        downloadStep4Form = new DownloadStep4();

        mainFramePanel.add(downloadStep1Form.getMainPanel(), DOWNLOAD1CARD);
        mainFramePanel.add(downloadStep2Form.getMainPanel(), DOWNLOAD2CARD);
        mainFramePanel.add(downloadStep3Form.getMainPanel(), DOWNLOAD3CARD);
        mainFramePanel.add(downloadStep4Form.getMainPanel(), DOWNLOAD4CARD);


//        add(mainForm.getMainPanel());


        setMinimumSize(new Dimension(460, 180));
        setSize(1500,1024);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

    }


    public void showNextCard() {
        CardLayout cardLayout = (CardLayout)mainFramePanel.getLayout();
        cardLayout.next(mainFramePanel);
    }

    public void showFirstStep() {
        showCard(DOWNLOAD1CARD);
    }

    public void showCard(String cardname) {
        CardLayout cardLayout = (CardLayout)mainFramePanel.getLayout();
        cardLayout.show(mainFramePanel, cardname);
    }

    public void showPreviousCard() {
        CardLayout cardLayout = (CardLayout)mainFramePanel.getLayout();
        cardLayout.previous(mainFramePanel);
    }

    public File showDestinationDirChooser() {
        return showDestinationDirChooser(OsUtils.getUserHome());
    }

    public File showDestinationDirChooser(String path) {
        fileChooser = null;
        File file = null;

        fileChooser = new JFileChooser(path);

        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if(fileChooser != null) {
            int returnVal = fileChooser.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
            }


        }
        return file;
    }

    public DownloadStep1 getDownloadStep1Form() {
        return downloadStep1Form;
    }

    public DownloadStep2 getDownloadStep2Form() {
        return downloadStep2Form;
    }

    public DownloadStep3 getDownloadStep3Form() {
        return downloadStep3Form;
    }

    public DownloadStep4 getDownloadStep4Form() {
        return downloadStep4Form;
    }
}