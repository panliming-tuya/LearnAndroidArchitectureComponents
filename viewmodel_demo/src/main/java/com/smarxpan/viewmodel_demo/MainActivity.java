/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.smarxpan.viewmodel_demo;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ScoreViewModel mViewModel;
    private TextView scoreViewA;
    private TextView scoreViewB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreViewA = findViewById(R.id.team_a_score);
        scoreViewB = findViewById(R.id.team_b_score);

        mViewModel = ViewModelProviders.of(this).get(ScoreViewModel.class);

        displayView(scoreViewA, mViewModel.scoreTeamA);
        displayView(scoreViewB, mViewModel.scoreTeamB);
    }

    /**
     * 增加A队的分数
     */
    public void increaseTeamA(View v) {
        mViewModel.scoreTeamA = mViewModel.scoreTeamA + 1;
        displayView(scoreViewA, mViewModel.scoreTeamA);
    }

    /**
     * 增加B队的分数
     */
    public void increaseTeamB(View v) {
        mViewModel.scoreTeamB = mViewModel.scoreTeamB + 1;
        displayView(scoreViewB, mViewModel.scoreTeamB);
    }

    /**
     * 重置所有分数
     */
    public void resetScore(View v) {
        mViewModel.scoreTeamA = 0;
        mViewModel.scoreTeamB = 0;
        displayView(scoreViewA, mViewModel.scoreTeamA);
        displayView(scoreViewB, mViewModel.scoreTeamB);
    }

    private void displayView(TextView scoreView, int scoreTeam) {
        scoreView.setText(String.valueOf(scoreTeam));
    }

}
