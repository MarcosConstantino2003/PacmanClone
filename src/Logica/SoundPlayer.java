// Source code is decompiled from a .class file using FernFlower decompiler.
package Logica;

import java.io.BufferedInputStream;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {
   private Clip munchClip;
   private Clip ghostClip;
   private Clip introClip;
   private Clip deathClip;
   private Clip winClip;
   private Clip gameoverClip;
   private Clip powerupClip;
   private Clip musicClip;
   private Clip touchClip;
   private Clip oneupClip;

   public SoundPlayer() {
      try {
         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(this.getClass().getResourceAsStream("/Sonidos/intro.wav")));
         this.introClip = AudioSystem.getClip();
         this.introClip.open(audioInputStream);
         AudioInputStream audioMunch = AudioSystem.getAudioInputStream(new BufferedInputStream(this.getClass().getResourceAsStream("/Sonidos/chomp.wav")));
         this.munchClip = AudioSystem.getClip();
         this.munchClip.open(audioMunch);
         AudioInputStream audioGhost = AudioSystem.getAudioInputStream(new BufferedInputStream(this.getClass().getResourceAsStream("/Sonidos/eatghost.wav")));
         this.ghostClip = AudioSystem.getClip();
         this.ghostClip.open(audioGhost);
         AudioInputStream audioDeath = AudioSystem.getAudioInputStream(new BufferedInputStream(this.getClass().getResourceAsStream("/Sonidos/death.wav")));
         this.deathClip = AudioSystem.getClip();
         this.deathClip.open(audioDeath);
         AudioInputStream audioWin = AudioSystem.getAudioInputStream(new BufferedInputStream(this.getClass().getResourceAsStream("/Sonidos/win.wav")));
         this.winClip = AudioSystem.getClip();
         this.winClip.open(audioWin);
         AudioInputStream audioGameover = AudioSystem.getAudioInputStream(new BufferedInputStream(this.getClass().getResourceAsStream("/Sonidos/gameover.wav")));
         this.gameoverClip = AudioSystem.getClip();
         this.gameoverClip.open(audioGameover);
         AudioInputStream audioPowerup = AudioSystem.getAudioInputStream(new BufferedInputStream(this.getClass().getResourceAsStream("/Sonidos/powerup.wav")));
         this.powerupClip = AudioSystem.getClip();
         this.powerupClip.open(audioPowerup);
         AudioInputStream audioMusic = AudioSystem.getAudioInputStream(new BufferedInputStream(this.getClass().getResourceAsStream("/Sonidos/music.wav")));
         this.musicClip = AudioSystem.getClip();
         this.musicClip.open(audioMusic);
         AudioInputStream audioTouch = AudioSystem.getAudioInputStream(new BufferedInputStream(this.getClass().getResourceAsStream("/Sonidos/touch.wav")));
         this.touchClip = AudioSystem.getClip();
         this.touchClip.open(audioTouch);
         AudioInputStream audioOneup = AudioSystem.getAudioInputStream(new BufferedInputStream(this.getClass().getResourceAsStream("/Sonidos/1up.wav")));
         this.oneupClip = AudioSystem.getClip();
         this.oneupClip.open(audioOneup);
      } catch (IOException | UnsupportedAudioFileException | LineUnavailableException var11) {
         var11.printStackTrace();
      }

   }

   public void playMunchSound() {
      if (this.munchClip != null && !this.munchClip.isRunning()) {
         this.munchClip.setFramePosition(0);
         this.munchClip.start();
      }

   }

   public void playGhostSound() {
      if (this.ghostClip != null) {
         this.ghostClip.setFramePosition(0);
         this.ghostClip.start();
      }

   }

   public void playIntroSound() {
      if (this.introClip != null) {
         this.introClip.setFramePosition(0);
         this.introClip.start();
      }

   }

   public void stopIntroSound() {
      if (this.introClip != null) {
         this.introClip.stop();
      }

   }

   public void playDeathSound() {
      if (this.deathClip != null) {
         this.deathClip.setFramePosition(0);
         this.deathClip.start();
      }

   }

   public void playMusic() {
      if (this.musicClip != null) {
         this.musicClip.setFramePosition(0);
         this.musicClip.start();
      }

   }

   public void stopMusic() {
      if (this.musicClip != null) {
         this.musicClip.stop();
      }

   }

   public void playWinSound() {
      if (this.winClip != null) {
         this.winClip.setFramePosition(0);
         this.winClip.start();
      }

   }

   public void playGameOverSound() {
      if (this.gameoverClip != null) {
         this.gameoverClip.setFramePosition(0);
         this.gameoverClip.start();
      }

   }

   public void playPowerUpSound() {
      if (this.powerupClip != null) {
         this.powerupClip.setFramePosition(0);
         this.powerupClip.start();
      }

   }

   public void playOneUpSound() {
      if (this.oneupClip != null) {
         this.oneupClip.setFramePosition(0);
         this.oneupClip.start();
      }

   }

   public void playTouchSound() {
      if (this.touchClip != null) {
         this.touchClip.setFramePosition(0);
         this.touchClip.start();
      }

   }
}
