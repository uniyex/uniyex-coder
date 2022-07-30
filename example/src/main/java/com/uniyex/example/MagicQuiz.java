package com.uniyex.example;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** The type Magic quiz. */
public class MagicQuiz {
  static final String LICENCE =
    """
    == License
    image::uniyex-coder-wecom-qr.png[WeCom,200,200,float=right]
    - Copyright © 2022 Uniyex LLC. All rights reserved. +
    - Magic Quiz is Open Source software released under the https://www.apache.org/licenses/LICENSE-2.0.html[Apache 2.0 license].
    - GitHub: https://github.com/uniyex/uniyex-coder
    - Email: uniyex@hotmail.com
    - Scan the QR code to communicate.
    """;

  /** The Binary pattern. */
  static final String BINARY_PATTERN = "00000000";

  /** The constant PRINT_LINE_SIZE. */
  static final int PRINT_LINE_SIZE = 129;

  /** The Print line. */
  static final String PRINT_LINE = new String(new char[PRINT_LINE_SIZE]).replace('\0', '-');

  /** The Quiz words. */
  static final String QUIZ_WORDS =
      "five,six,nine,ten,pen,pencil,paper,tape,dog,turtle,cat,mouse,apple,banana,lime,peach,chant,write,run,sit,brown,blue,"
          + "red,pink,yo-yo,doll,block,kite,hot,dry,cool,wet,tall,small,fit,cute,pond,dune,sea,cave,bow,wig,cap,pin,foot,leg,hip,elbow,dance,hum,"
          + "drive,ask,king,crown,princess,prince,yam,pea,carrot,onion,flag,toy,globe,book,watch,bib,ring,bag,open,sofa,bench,quick,nest,hut,barn,igloo,"
          + "sister,mom,dad,uncle,Greece,Korea,France,Mexico,cousin,smart,aunt,strong,doctor,cook,vet,baker,judo,ping pong,chess,golf,spoon,pot,jug,cup,"
          + "eagle,owl,swan,parrot,rug,TV,lamp,desk,duck,cow,pig,hen,moon,star,comet,cloud,deer,ram,ox,yak,Monday,Thursday,Friday,Saturday,t-shirt,vest,coat,cape,"
          + "mall,pool,park,airport,train,jet,car,ship,hike,garden,fish,jog,tent,backpack,lantern,map,tulip,lily,rose,sunflower,twenty,forty,hundred,thirty,runny nose,headache,cold,toothache,"
          + "seesaw,swing,slide,maze,March,June,July,December,zebra,elephant,lane,road,crayon,ruler,clay,eraser,flute,piano,cello,drums,spicy,sour,sweet,bitter,"
          + "turn,left,right,cross,school,gym,toy store,zoo,cook,fry,chop,peel";

  /** # card lists: [card-1,card-2,card-n] ## card-1...n: [1 quiz-1, 2 quiz-2, 3 quiz-n] */
  static List<List<String>> cardLists = new ArrayList<>();

  /*
   * 初始化卡片容器的数量，其中每一个List容器用于存放二进制每个位置为1的对应的数字及谜语；
   * 比如数字12的八位二进制为00001100，其中从右至左第三、四的位置为1，则将数字12分别存放至第三、四张卡片上去；
   */
  static {
    for (int i = 0; i < BINARY_PATTERN.length(); i++) {
      cardLists.add(new ArrayList<>());
    }
  }

  /** Quiz. */
  public static void quiz(boolean quizzed) {
    handleCardLists();
    int result = 0;
    for (int x = 0; x < cardLists.size(); x++) {
      printCard(x);
      if (quizzed) {
        System.out.println("有你心中所想吗？");
        final Scanner scanner = new Scanner(System.in);
        if (scanner.next().equalsIgnoreCase("y")) {
          result += Integer.parseInt(cardLists.get(x).get(0).split(" ")[0]);
        }
        System.out.println("\n我猜，你心里想的是：" + result);
      }
    }
  }

  private static void handleCardLists() {
    final String[] splitStrings = QUIZ_WORDS.split(",");
    // 将数字和文字放入二进制位对应的卡片中
    for (int i = 0; i < splitStrings.length; i++) {
      // String leftPaddingZeroBinary = df.format(Long.valueOf(Integer.toBinaryString(i + 1)));
      final String leftPaddingZeroBinary = Integer.toBinaryString(i + 1);
      final String reverseLeftPaddingZeroBinary = StringUtils.reverse(leftPaddingZeroBinary);
      final char[] chars = reverseLeftPaddingZeroBinary.toCharArray();
      for (int j = chars.length - 1; j >= 0; j--) {
        if (String.valueOf(chars[j]).equals("1")) {
          cardLists.get(j).add((i + 1) + " " + splitStrings[i]);
        }
      }
    }
  }

  private static void printCard(int x) {
    List<String> quizList = cardLists.get(x);
    System.out.println();
    System.out.println();
    System.out.println(PRINT_LINE);
    System.out.println(
        "|" + StringUtils.center("第 " + (x + 1) + " 张卡片", PRINT_LINE_SIZE - 5, "") + "|");
    System.out.println(PRINT_LINE);
    for (int y = 0; y < quizList.size(); y++) {
      if (y != 0 && y % 8 == 0) {
        System.out.print("|");
        System.out.println();
      }
      // System.out.print(String.format(OUT_FORMAT, quizList.get(y)) + " | ");
      System.out.print("| " + StringUtils.rightPad(quizList.get(y), 14, " "));
    }
    System.out.print("|");
    System.out.println();
    if (x == cardLists.size() - 1) {
      System.out.println(PRINT_LINE);
    }
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) throws InterruptedException {
    int secondsOfThinking = 5;
    System.out.println("``` ");
    System.out.println(PRINT_LINE);
    System.out.println(LICENCE);
    System.out.println(PRINT_LINE);
    String[] split = QUIZ_WORDS.split(",");
    for (int i = 0; i < split.length; i++) {
      String s = split[i];
      if (i != 0 && i % (BINARY_PATTERN.length() << 1) == 0) {
        System.out.println();
      }
      if (i == split.length - 1) {
        System.out.println(s);
      } else {
        System.out.print(s + ",");
      }
    }
    System.out.println(PRINT_LINE);
    System.out.println("请从上面选择一个字，然后写下来，不要告诉任何人！ " + secondsOfThinking + "秒之后开始……");
    System.out.println(PRINT_LINE);
    Thread.sleep(secondsOfThinking * 1000);

    quiz(false);
  }
}
