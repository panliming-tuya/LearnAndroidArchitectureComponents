package com.smarxpan.viewmodel_demo;

import android.arch.lifecycle.ViewModel;

public class ScoreViewModel extends ViewModel {
   // Tracks the score for Team A
   public int scoreTeamA = 0;

   // Tracks the score for Team B
   public int scoreTeamB = 0;
}