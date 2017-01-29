/*
* Description:
*	I can never beat me wife over the game 24 points, so I write this program. 
* 	This piece of code can calculate 24 Points game.
* 	You can either get 4 cards from a 52 cards deck or input what ever 4 numbers.
*	When there are multiple answers, just return one. If no possible answer, return false.
* Author:
*	Chenqi Zhang
* Date:
*	2017-1-28
*/

import java.util.*;
public class Point24{
	public static void main(String[] args){
		// get 4 rendom cards from the deck
		Deck deck = new Deck();
		Card[] cards = deck.get4Cards();
		for(Card card : cards){
			System.out.println(card.color+" "+card.num);
		}
		List<Card> list = new ArrayList<Card>();
		for(Card c: cards){
			list.add(c);
		}
		System.out.println(Point24.getAnswer(list,""));

		// input whatever 4 numbers
		// List<Card> list = new ArrayList<>();
		// int[] input = new int[]{3,13,5,7};
		// for(int i: input){
		// 	list.add(new Card("",i));
		// }
		// System.out.println(Point24.getAnswer(list,""));
	}
	public static boolean getAnswer(List<Card> cards,String str){
		boolean flag = false;
		if(cards.size()==1){
			if(cards.get(0).num == 24){
				System.out.println(str);
				return true;
			}else{
				return false;
			}
		}
		for(int i=0;i<cards.size();i++){
			for(int j=i+1;j<cards.size();j++){
				int a = cards.get(i).num;
				int b = cards.get(j).num;
				List<Card> newCards = new ArrayList<>();
				for(int k=0;k<cards.size();k++){
					if(k!=i && k != j){
						newCards.add(cards.get(k));
					}
				}
				// +
				List<Card> list = new ArrayList<>(newCards);
				list.add(new Card("",a+b));
				flag = flag || getAnswer(list,str+a+"+"+b+"-->");
				// *
				list = new ArrayList<>(newCards);
				list.add(new Card("",a*b));
				flag = flag || getAnswer(list,str+a+"*"+b+"-->");
				// -
				if(a>b){
					list = new ArrayList<Card>(newCards);
					list.add(new Card("",a-b));
					flag = flag || getAnswer(list,str+a+"-"+b+"-->");
				}else if(a<b){
					list = new ArrayList<Card>(newCards);
					list.add(new Card("",b-a));
					flag = flag || getAnswer(list,str+b+"-"+a+"-->");
				}
				// /
				if(a!= 0){
					list = new ArrayList<Card>(newCards);
					list.add(new Card("",b/a));
					flag = flag || getAnswer(list,str+b+"/"+a+"-->");
				}
				if(b!=0){
					list = new ArrayList<Card>(newCards);
					list.add(new Card("",a/b));
					flag = flag || getAnswer(list,str+a+"/"+b+"-->");
				}
			}
		}
		return flag;
	}
}
class Deck{
	List<Card> cards;
	public Deck(){
		// cards initial
		cards = new ArrayList<>();
		for(int i=1;i<14;i++){
			cards.add(new Card("heart",i));
			cards.add(new Card("spade",i));
			cards.add(new Card("club",i));
			cards.add(new Card("diamond",i));
		}
	}
	public Card[] get4Cards(){
		Card[] res = new Card[4];
		Random random = new Random();
		for(int i=0;i<4;){
			int rand = random.nextInt(53 - 1) + 1;
			if(!cards.get(rand).picked){
				cards.get(rand).picked = true;
				res[i] = cards.get(rand);
				i++;
			}
		}
		return res;
	}
	public void refreshDeck(){
		for(int i=0;i<cards.size();i++){
			cards.get(i).picked = false;
		}
	}
}

class Card{
	String color;
	int num;
	boolean picked;
	public Card(String color, int num){
		this.color = color;
		this.num = num;
		this.picked = false;
	}
}