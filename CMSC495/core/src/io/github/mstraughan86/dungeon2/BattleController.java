package io.github.mstraughan86.dungeon2;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.mstraughan86.dungeon2.Characters.BattleCharacter;
import io.github.mstraughan86.dungeon2.Characters.EnemyParty;
import io.github.mstraughan86.dungeon2.Characters.NPC.NPC;
import io.github.mstraughan86.dungeon2.Characters.PC.PC;
import io.github.mstraughan86.dungeon2.Characters.PCParty;
import io.github.mstraughan86.dungeon2.Characters.Skills.Skill;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class BattleController extends Stage{

    private ArrayList<PC> playerArray;
    private ArrayList<NPC> enemyArray;
    private ArrayList<BattleCharacter> allArray;

    private ArrayList<ArrayList<Skill>> skillList;

    private ArrayDeque<BattleCharacter> turnQueue;
    private boolean waitForPlayerInput;
    private boolean needTarget;
    private boolean gotTarget;
    private BattleCharacter currentPlayer;
    private BattleCharacter targetPlayer;
    private Integer characterNum = null;
    private Integer characterSkill = null;

    Group p1UI, p2UI, p3UI;
    TextButton p1Skills, p2Skills, p3Skills;
    TextButton p1Items, p2Items, p3Items;
    Table p1BasicActions, p2BasicActions, p3BasicActions;
    Table p1SpecificActions, p2SpecificActions, p3SpecificActions;
    Table p1ItemList, p2ItemList, p3ItemList;
    Label p1Name, p2Name, p3Name;
    Label p1HP, p1MP, p2HP, p2MP, p3HP, p3MP;
    Label e1HP, e2HP, e3HP, e4HP;
    ArrayList messages;
    Label msg1, msg2, msg3, msg4, msg5;

    Music battleMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/JRPG_battle_loop.ogg"));

    public BattleController(Viewport viewport) {
        super(viewport);

        playerArray = new ArrayList<PC>();
        enemyArray = new ArrayList<NPC>();
        allArray = new ArrayList<BattleCharacter>();
        skillList = new ArrayList<ArrayList<Skill>>();
        turnQueue = new ArrayDeque();

        for (int i = 0; i < PCParty.INSTANCE.getParty().length; i++) {
            playerArray.add(PCParty.INSTANCE.getParty()[i]);
            allArray.add(playerArray.get(i));
            this.addActor(playerArray.get(i));
            playerArray.get(i).setX(100 + (100*(i+1)));
            playerArray.get(i).setY(190);

            skillList.add(new ArrayList<Skill>());
            skillList.get(i).add(playerArray.get(i).getPrimarySkill());
            skillList.get(i).add(playerArray.get(i).getSecondarySkill());
            skillList.get(i).add(playerArray.get(i).getSupportSkill());

            for (int j = 0; j <  playerArray.get(i).getSkills().size(); j++) {
                skillList.get(i).add(playerArray.get(i).getSkills().get(j));
            }
            playerArray.get(i).setAlive(true);
        }

        EnemyParty e = new EnemyParty();

        for (int i = 0; i < e.getParty().length; i++) {
            enemyArray.add(e.getParty()[i]);
            allArray.add(enemyArray.get(i));
            this.addActor(enemyArray.get(i));
            enemyArray.get(i).setX((-60) + (140*(i+1)));
            enemyArray.get(i).setY(280);

            skillList.add(new ArrayList<Skill>());
            for (int j = 0; j <  enemyArray.get(i).getSkills().size(); j++) {
                skillList.get(i+3).add(enemyArray.get(i).getSkills().get(j));
            }
        }

        for (BattleCharacter bc : allArray) {
            bc.resetCurrentHP();
            bc.resetCurrentMP();
        }

        waitForPlayerInput = false;
        needTarget = false;

        buildUI();

        tempCancelButton();
    }

    public void tempCancelButton() {
        TextButton cancelButton = UIFactory.textButton("CANCEL");

        cancelButton.setSize(160,50);
        cancelButton.setPosition(0,420);

        cancelButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                event.cancel();
                StageController.INSTANCE.setStage(StageController.INSTANCE.previousStage);
                battleMusic.dispose();
            }
        });

        this.addActor(cancelButton);
    }

    public void battleLoop() {
        battleMusic.play();
        buildTurnQueue();
        currentPlayerTurn();
        updateUI();
        doAction();

        updateChars();
    }

    private void buildTurnQueue() {
        if (waitForPlayerInput) {
            return;
        }

        while (turnQueue.isEmpty()) {
            for (BattleCharacter p : allArray) {
                if (p instanceof PC) {
                    if (((PC) p).getAlive()) {
                        p.stepTurnPoints();
                    } else {
                        break;
                    }
                } else {
                    p.stepTurnPoints();
                }
            }
            for (BattleCharacter p : allArray) {
                if (p.getTurnPoints() >= 100) {
                    turnQueue.add(p);
                }
            }
        }
    }

    private void currentPlayerTurn() {
        if (waitForPlayerInput) {
            return;
        }

        BattleCharacter p = turnQueue.pop();
        currentPlayer = p;
        if (p instanceof PC) {
            waitForPlayerInput = true;
            if(p.equals(playerArray.get(0))) {
                p1UI.setVisible(true);
            } else if (p.equals(playerArray.get(1))) {
                p2UI.setVisible(true);
            } else if (p.equals(playerArray.get(2))) {
                p3UI.setVisible(true);
            }
        } else if (p instanceof NPC){
            ((NPC) p).action(currentPlayer, playerArray, enemyArray);
        }
        p.setTurnPoints(0);

        //can add actual turn logic here.
        // current player attacks other player, display hp
        //after any change in hp, check if hp is <0. if so, remove from playerArray

        //sometime during the players turn, reset turnPoints to 0
        //sometime during the players turn, remove from turnOrderArray


    }

    private void doAction() {

        if (characterSkill == null) {
            return;
        } else if (needTarget && !gotTarget) {
            return;
        } else if (needTarget && gotTarget){
            skillList.get(characterNum).get(characterSkill).doSkill(currentPlayer, targetPlayer, playerArray, enemyArray);
            endTurn();
        } else {
            skillList.get(characterNum).get(characterSkill).doSkill(currentPlayer, targetPlayer, playerArray, enemyArray);
            endTurn();
        }
    }

    private void updateChars() {

        for (PC pc : playerArray) {
            if (pc.getCurrentHP() <= 0) {
                pc.setAlive(false);
                allArray.remove(pc);
            }
        }

        for (BattleCharacter bc : enemyArray) {
            if (bc.getCurrentHP() <= 0) {
                bc.remove();
                allArray.remove(bc);
            }
        }
    }


    private void updateUI() {
        p1HP.setText("HP: " + playerArray.get(0).getCurrentHP() + " / " + playerArray.get(0).getMaxHP());
        p1MP.setText("MP: " + playerArray.get(0).getCurrentMP() + " / " + playerArray.get(0).getMaxMP());
        p2HP.setText("HP: " + playerArray.get(1).getCurrentHP() + " / " + playerArray.get(1).getMaxHP());
        p2MP.setText("MP: " + playerArray.get(1).getCurrentMP() + " / " + playerArray.get(1).getMaxMP());
        p3HP.setText("HP: " + playerArray.get(2).getCurrentHP() + " / " + playerArray.get(2).getMaxHP());
        p3MP.setText("MP: " + playerArray.get(2).getCurrentMP() + " / " + playerArray.get(2).getMaxMP());

        if (!(enemyArray.get(0).getStage() == null)) {
            e1HP.setText(enemyArray.get(0).getCurrentHP() + " / " + enemyArray.get(0).getMaxHP());
        }
        if (!(enemyArray.get(1).getStage() == null)) {
            e2HP.setText(enemyArray.get(1).getCurrentHP() + " / " + enemyArray.get(1).getMaxHP());
        }
        if (!(enemyArray.get(2).getStage() == null)) {
            e3HP.setText(enemyArray.get(2).getCurrentHP() + " / " + enemyArray.get(2).getMaxHP());
        }

        p1Name.setColor(Color.WHITE);
        p2Name.setColor(Color.WHITE);
        p3Name.setColor(Color.WHITE);

        if (currentPlayer.equals(playerArray.get(0))) {
            p1Name.setColor(Color.YELLOW);
        } else if (currentPlayer.equals(playerArray.get(1))) {
            p2Name.setColor(Color.YELLOW);
        } else if (currentPlayer.equals(playerArray.get(2))) {
            p3Name.setColor(Color.YELLOW);
        }

        if (!playerArray.get(0).getAlive()) {
            p1Name.setColor(Color.RED);
        }
        if (!playerArray.get(1).getAlive()) {
            p2Name.setColor(Color.RED);
        }
        if (!playerArray.get(2).getAlive()) {
            p3Name.setColor(Color.RED);
        }

        msg1.setText((String) messages.get(0));
        msg2.setText((String) messages.get(1));
        msg3.setText((String) messages.get(2));
        msg4.setText((String) messages.get(3));
        msg5.setText((String) messages.get(4));
    }

    private void endTurn() {
        waitForPlayerInput = false;
        needTarget = false;
        gotTarget = false;
        currentPlayer = null;
        targetPlayer = null;
        characterNum = null;
        characterSkill = null;

        p1UI.setVisible(false);
        p2UI.setVisible(false);
        p3UI.setVisible(false);

        p1Skills.setChecked(false);
        p1Items.setChecked(false);
        p1SpecificActions.setVisible(false);
        p1ItemList.setVisible(false);
        p2Skills.setChecked(false);
        p2Items.setChecked(false);
        p2SpecificActions.setVisible(false);
        p2ItemList.setVisible(false);
        p3Skills.setChecked(false);
        p3Items.setChecked(false);
        p3SpecificActions.setVisible(false);
        p3ItemList.setVisible(false);
    }

    public void textOutput(String string) {
        if (messages.size() < 5) {
            messages.add(string);
        } else if (messages.size() == 5) {
            messages.remove(0);
            messages.add(string);
        }
    }

    public void buildUI() {

        final Table actionHistory = new Table();
        actionHistory.setHeight(180);
        actionHistory.setWidth(this.getWidth());
        actionHistory.setX(0);
        actionHistory.setY(300);

        messages = new ArrayList(5);
        textOutput("");
        textOutput("");
        textOutput("");
        textOutput("");
        textOutput("");
        this.addActor(actionHistory);

        msg1 = UIFactory.label("");
        msg2 = UIFactory.label("");
        msg3 = UIFactory.label("");
        msg4 = UIFactory.label("");
        msg5 = UIFactory.label("");

        actionHistory.align(Align.top);
        actionHistory.add(msg1);
        actionHistory.row();
        actionHistory.add(msg2);
        actionHistory.row();
        actionHistory.add(msg3);
        actionHistory.row();
        actionHistory.add(msg4);
        actionHistory.row();
        actionHistory.add(msg5);

        final Table charInfo = new Table();

        p1BasicActions = new Table();
        p1SpecificActions = new Table();
        final SplitPane p1SplitPane1 = UIFactory.splitPane(p1BasicActions, p1SpecificActions);
        final SplitPane p1SplitPane2 = UIFactory.splitPane(p1SplitPane1, charInfo);
        p1ItemList = new Table();
        p1UI = new Group();
        p1UI.addActor(p1ItemList);
        p1UI.addActor(p1SplitPane2);

        p2BasicActions = new Table();
        p2SpecificActions = new Table();
        final SplitPane p2SplitPane1 = UIFactory.splitPane(p2BasicActions, p2SpecificActions);
        final SplitPane p2SplitPane2 = UIFactory.splitPane(p2SplitPane1, charInfo);
        p2ItemList = new Table();
        p2UI = new Group();
        p2UI.addActor(p2ItemList);
        p2UI.addActor(p2SplitPane2);

        p3BasicActions = new Table();
        p3SpecificActions = new Table();
        final SplitPane p3SplitPane1 = UIFactory.splitPane(p3BasicActions, p3SpecificActions);
        final SplitPane p3SplitPane2 = UIFactory.splitPane(p3SplitPane1, charInfo);
        p3ItemList = new Table();
        p3UI = new Group();
        p3UI.addActor(p3ItemList);
        p3UI.addActor(p3SplitPane2);

        ///////

        // Add Item List Table Specifications

        ///////

        p1SplitPane2.setWidth(this.getWidth());
        p1SplitPane2.setHeight(this.getHeight() * 2 / 5);
        p1SplitPane2.setSplitAmount(2f/3f);
        p1SplitPane2.setMinSplitAmount(2f/3f);
        p1SplitPane2.setMaxSplitAmount(2.001f/3f);
        p1SplitPane1.setSplitAmount(1f/4f);
        p1SplitPane1.setMinSplitAmount(1f/4f);
        p1SplitPane1.setMaxSplitAmount(1.001f/4f);

        p2SplitPane2.setWidth(this.getWidth());
        p2SplitPane2.setHeight(this.getHeight() * 2 / 5);
        p2SplitPane2.setSplitAmount(2f/3f);
        p2SplitPane2.setMinSplitAmount(2f/3f);
        p2SplitPane2.setMaxSplitAmount(2.001f/3f);
        p2SplitPane1.setSplitAmount(1f/4f);
        p2SplitPane1.setMinSplitAmount(1f/4f);
        p2SplitPane1.setMaxSplitAmount(1.001f/4f);

        p3SplitPane2.setWidth(this.getWidth());
        p3SplitPane2.setHeight(this.getHeight() * 2 / 5);
        p3SplitPane2.setSplitAmount(2f/3f);
        p3SplitPane2.setMinSplitAmount(2f/3f);
        p3SplitPane2.setMaxSplitAmount(2.001f/3f);
        p3SplitPane1.setSplitAmount(1f/4f);
        p3SplitPane1.setMinSplitAmount(1f/4f);
        p3SplitPane1.setMaxSplitAmount(1.001f/4f);

        ///////

        charInfo.align(Align.center|Align.top);

        p1BasicActions.align(Align.center);
        p1SpecificActions.align(Align.topLeft);

        p2BasicActions.align(Align.center);
        p2SpecificActions.align(Align.topLeft);

        p3BasicActions.align(Align.center);
        p3SpecificActions.align(Align.topLeft);

        TextButton p1PrimarySkill = UIFactory.textButton(playerArray.get(0).getPrimarySkill().getName());
        TextButton p1SecondarySkill = UIFactory.textButton(playerArray.get(0).getSecondarySkill().getName());
        p1Skills = UIFactory.textButton(playerArray.get(0).getSkillsName(), "submenu");
        p1Items = UIFactory.textButton("Items", "submenu");
        TextButton p1SupportSkill = UIFactory.textButton(playerArray.get(0).getSupportSkill().getName());

        p1BasicActions.add(p1PrimarySkill).width(106f).height(37.4f).padBottom(1);
        p1BasicActions.row();
        p1BasicActions.add(p1SecondarySkill).width(106f).height(37.4f).padBottom(1);
        p1BasicActions.row();
        p1BasicActions.add(p1Skills).width(106f).height(37.4f).padBottom(1);
        p1BasicActions.row();
        p1BasicActions.add(p1Items).width(106f).height(37.4f).padBottom(1);
        p1BasicActions.row();
        p1BasicActions.add(p1SupportSkill).width(106f).height(37.4f);

        TextButton p2PrimarySkill = UIFactory.textButton(playerArray.get(1).getPrimarySkill().getName());
        TextButton p2SecondarySkill = UIFactory.textButton(playerArray.get(1).getSecondarySkill().getName());
        p2Skills = UIFactory.textButton(playerArray.get(1).getSkillsName(), "submenu");
        p2Items = UIFactory.textButton("Items");
        TextButton p2SupportSkill = UIFactory.textButton(playerArray.get(1).getSupportSkill().getName());

        p2BasicActions.add(p2PrimarySkill).width(106f).height(37.4f).padBottom(1);
        p2BasicActions.row();
        p2BasicActions.add(p2SecondarySkill).width(106f).height(37.4f).padBottom(1);
        p2BasicActions.row();
        p2BasicActions.add(p2Skills).width(106f).height(37.4f).padBottom(1);
        p2BasicActions.row();
        p2BasicActions.add(p2Items).width(106f).height(37.4f).padBottom(1);
        p2BasicActions.row();
        p2BasicActions.add(p2SupportSkill).width(106f).height(37.4f);

        TextButton p3PrimarySkill = UIFactory.textButton(playerArray.get(2).getPrimarySkill().getName());
        TextButton p3SecondarySkill = UIFactory.textButton(playerArray.get(2).getSecondarySkill().getName());
        p3Skills = UIFactory.textButton(playerArray.get(2).getSkillsName(), "submenu");
        p3Items = UIFactory.textButton("Items");
        TextButton p3SupportSkill = UIFactory.textButton(playerArray.get(2).getSupportSkill().getName());

        p3BasicActions.add(p3PrimarySkill).width(106f).height(37.4f).padBottom(1);
        p3BasicActions.row();
        p3BasicActions.add(p3SecondarySkill).width(106f).height(37.4f).padBottom(1);
        p3BasicActions.row();
        p3BasicActions.add(p3Skills).width(106f).height(37.4f).padBottom(1);
        p3BasicActions.row();
        p3BasicActions.add(p3Items).width(106f).height(37.4f).padBottom(1);
        p3BasicActions.row();
        p3BasicActions.add(p3SupportSkill).width(106f).height(37.4f);

        ///////

        for (int i = 0; i < playerArray.get(0).getSkills().size(); i++) {
            TextButton temp = UIFactory.textButton(playerArray.get(0).getSkills().get(i).getName());
            p1SpecificActions.add(temp).width(106f).height(37.4f).padBottom(1);
            p1SpecificActions.row();

            final int skill = i + 3;

            if (playerArray.get(0).getSkills().get(i).getNeedsTarget()) {
                temp.addListener(new ChangeListener() {
                    public void changed (ChangeEvent event, Actor actor) {
                        needTarget = true;
                        characterNum = 0;
                        characterSkill = skill;
                    }
                });
            } else {
                temp.addListener(new ChangeListener() {
                    public void changed (ChangeEvent event, Actor actor) {
                        needTarget = false;
                        characterNum = 0;
                        characterSkill = skill;
                    }
                });
            }
        }

        p1PrimarySkill.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                if (playerArray.get(0).getPrimarySkill().getNeedsTarget()) {
                    needTarget = true;
                } else {
                    needTarget = false;
                }
                characterNum = 0;
                characterSkill = 0;
            }
        });

        p1SecondarySkill.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                if (playerArray.get(0).getSecondarySkill().getNeedsTarget()) {
                    needTarget = true;
                } else {
                    needTarget = false;
                }
                characterNum = 0;
                characterSkill = 1;
            }
        });

        p1Skills.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                p1SpecificActions.setVisible(!p1SpecificActions.isVisible());
            }
        });

        p1Items.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                //create something similar to p1Skills
                //this will need an inventory array in the party class that is shared by all characters.
                // possibly dont need to duplicate the button, just make the same button for all 3 PC?
            }
        });

        p1SupportSkill.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                if (playerArray.get(0).getSupportSkill().getNeedsTarget()) {
                    needTarget = true;
                } else {
                    needTarget = false;
                }
                characterNum = 0;
                characterSkill = 2;
                endTurn();
            }
        });

        for (int i = 0; i < playerArray.get(1).getSkills().size(); i++) {
            TextButton temp = UIFactory.textButton(playerArray.get(1).getSkills().get(i).getName());
            p2SpecificActions.add(temp).width(106f).height(37.4f).padBottom(1);
            p2SpecificActions.row();
            final int skill = i + 3;

            if (playerArray.get(1).getSkills().get(i).getNeedsTarget()) {
                temp.addListener(new ChangeListener() {
                    public void changed (ChangeEvent event, Actor actor) {
                        needTarget = true;
                        characterNum = 1;
                        characterSkill = skill;
                    }
                });
            } else {
                temp.addListener(new ChangeListener() {
                    public void changed (ChangeEvent event, Actor actor) {
                        needTarget = false;
                        characterNum = 1;
                        characterSkill = skill;
                    }
                });
            }
        }

        p2PrimarySkill.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                if (playerArray.get(1).getPrimarySkill().getNeedsTarget()) {
                    needTarget = true;
                } else {
                    needTarget = false;
                }
                characterNum = 1;
                characterSkill = 0;
            }
        });

        p2SecondarySkill.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                if (playerArray.get(1).getSecondarySkill().getNeedsTarget()) {
                    needTarget = true;
                } else {
                    needTarget = false;
                }
                characterNum = 1;
                characterSkill = 1;
            }
        });

        p2Skills.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                p2SpecificActions.setVisible(!p2SpecificActions.isVisible());
            }
        });

        p2Items.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                //create something similar to p1Skills
                //this will need an inventory array in the party class that is shared by all characters.
                // possibly dont need to duplicate the button, just make the same button for all 3 PC?
            }
        });

        p2SupportSkill.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                if (playerArray.get(1).getSupportSkill().getNeedsTarget()) {
                    needTarget = true;
                } else {
                    needTarget = false;
                }
                characterNum = 1;
                characterSkill = 2;
                endTurn();
            }
        });

        for (int i = 0; i < playerArray.get(2).getSkills().size(); i++) {
            TextButton temp = UIFactory.textButton(playerArray.get(2).getSkills().get(i).getName());
            p3SpecificActions.add(temp).width(106f).height(37.4f).padBottom(1);
            p3SpecificActions.row();
            final int skill = i + 3;

            if (playerArray.get(2).getSkills().get(i).getNeedsTarget()) {
                temp.addListener(new ChangeListener() {
                    public void changed (ChangeEvent event, Actor actor) {
                        needTarget = true;
                        characterNum = 2;
                        characterSkill = skill;
                    }
                });
            } else {
                temp.addListener(new ChangeListener() {
                    public void changed (ChangeEvent event, Actor actor) {
                        needTarget = false;
                        characterNum = 2;
                        characterSkill = skill;
                    }
                });
            }
        }

        p3PrimarySkill.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                if (playerArray.get(2).getPrimarySkill().getNeedsTarget()) {
                    needTarget = true;
                } else {
                    needTarget = false;
                }
                characterNum = 2;
                characterSkill = 0;
            }
        });

        p3SecondarySkill.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                if (playerArray.get(2).getSecondarySkill().getNeedsTarget()) {
                    needTarget = true;
                } else {
                    needTarget = false;
                }
                characterNum = 2;
                characterSkill = 1;
            }
        });

        p3Skills.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                p3SpecificActions.setVisible(!p3SpecificActions.isVisible());
            }
        });

        p3Items.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                //create something similar to p1Skills
                //this will need an inventory array in the party class that is shared by all characters.
                // possibly dont need to duplicate the button, just make the same button for all 3 PC?
            }
        });

        p3SupportSkill.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                if (playerArray.get(2).getSupportSkill().getNeedsTarget()) {
                    needTarget = true;
                } else {
                    needTarget = false;
                }
                characterNum = 2;
                characterSkill = 2;
                endTurn();
            }
        });

        ///////

        p1Name = UIFactory.label(playerArray.get(0).getName());
        p1HP = UIFactory.label(
                "HP: " + playerArray.get(0).getCurrentHP() + " / " + playerArray.get(0).getMaxHP());
        p1MP = UIFactory.label(
                "MP: " + playerArray.get(0).getCurrentMP() + " / " + playerArray.get(0).getMaxMP());
        p1MP.setFontScale(0.9f);

        p2Name = UIFactory.label(playerArray.get(1).getName());
        p2HP = UIFactory.label(
                "HP: " + playerArray.get(1).getCurrentHP() + " / " + playerArray.get(1).getMaxHP());
        p2MP = UIFactory.label(
                "MP: " + playerArray.get(1).getCurrentMP() + " / " + playerArray.get(1).getMaxMP());
        p2MP.setFontScale(0.9f);

        p3Name = UIFactory.label(playerArray.get(2).getName());
        p3HP = UIFactory.label(
                "HP: " + playerArray.get(2).getCurrentHP() + " / " + playerArray.get(2).getMaxHP());
        p3MP = UIFactory.label(
                "MP: " + playerArray.get(2).getCurrentMP() + " / " + playerArray.get(2).getMaxMP());
        p3MP.setFontScale(0.9f);

        charInfo.add(p1Name);
        charInfo.row();
        charInfo.add(p1HP).align((Align.left)).padRight(20);
        charInfo.add(p1MP).padBottom(10);
        charInfo.row();

        charInfo.add(p2Name).padTop(10);
        charInfo.row();
        charInfo.add(p2HP).align((Align.left)).padRight(20);
        charInfo.add(p2MP).padBottom(10);
        charInfo.row();

        charInfo.add(p3Name).padTop(10);
        charInfo.row();
        charInfo.add(p3HP).align((Align.left)).padRight(20);
        charInfo.add(p3MP).padBottom(10);
        charInfo.row();

        /////

        e1HP = UIFactory.label(
                enemyArray.get(0).getCurrentHP() + " / " + enemyArray.get(0).getCurrentHP());
        e2HP = UIFactory.label(
                enemyArray.get(1).getCurrentHP() + " / " + enemyArray.get(1).getCurrentHP());
        e3HP = UIFactory.label(
                enemyArray.get(2).getCurrentHP() + " / " + enemyArray.get(2).getCurrentHP());
        e4HP = UIFactory.label(
                enemyArray.get(3).getCurrentHP() + " / " + enemyArray.get(3).getCurrentHP());

        e1HP.setX(enemyArray.get(0).getX() - 10);
        e1HP.setY(enemyArray.get(0).getY() - 30);
        e2HP.setX(enemyArray.get(1).getX() - 10);
        e2HP.setY(enemyArray.get(1).getY() - 30);
        e3HP.setX(enemyArray.get(2).getX() - 10);
        e3HP.setY(enemyArray.get(2).getY() - 30);
        e4HP.setX(enemyArray.get(3).getX() - 10);
        e4HP.setY(enemyArray.get(3).getY() - 30);


        this.addActor(e1HP);
        this.addActor(e2HP);
        this.addActor(e3HP);
        this.addActor(e4HP);

        /////

        p1UI.setVisible(false);
        p2UI.setVisible(false);
        p3UI.setVisible(false);

        this.addActor(p1UI);
        this.addActor(p2UI);
        this.addActor(p3UI);



        //////

        p1SpecificActions.setVisible(false);
        p2SpecificActions.setVisible(false);
        p3SpecificActions.setVisible(false);
        p1Items.setVisible(false);
        p2Items.setVisible(false);
        p3Items.setVisible(false);
    }



    public boolean getWaitForPlayerInput() {
        return waitForPlayerInput;
    }

    public boolean getNeedTarget() {
        return needTarget;
    }

    public boolean getGotTarget() {
        return gotTarget;
    }

    public void setGotTarget(boolean bool) {
        gotTarget = bool;
    }

    public void setTarget(BattleCharacter character) {
        this.targetPlayer = character;
    }

}

