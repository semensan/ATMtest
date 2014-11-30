package myatm;

import customATMexceptions.NoCardInsertedException;
import customATMexceptions.NotEnoughtMoneyInATMexception;
import customATMexceptions.NotEnoughtMoneyInAccountException;

public class ATM {
    private double moneyInATM;
    private Card card;
    
	//ћожно задавать количество денег в банкомате 
    ATM(double moneyInATM){
         this.moneyInATM=moneyInATM;
         this.card=null;
    }

    // ¬озвращает каоличестов денег в банкомате
    public double getMoneyInATM() {
    	return moneyInATM;
    }
        
    //— вызова данного метода начинаетс€ работа с картой
    //ћетод принимает карту и пин-код, провер€ет пин-код карты и не заблокирована ли она
    //≈сли неправильный пин-код или карточка заблокирована, возвращаем false. ѕри этом, вызов всех последующих методов у ATM с данной картой должен генерировать исключение NoCardInserted
    public boolean validateCard(Card card, int pinCode){
    	if (!card.isBlocked())
    	if (card.checkPin(pinCode)) {
			this.card = card;
			return true;
		}
		
		return false;
    }
    
    //¬озвращает сколько денег есть на счету
    public double checkBalance() throws NoCardInsertedException {
    	if (card == null) throw new NoCardInsertedException("There is no card inserted");
		return card.getAccount().getBalance();
    }
    
    //ћетод дл€ сн€ти€ указанной суммы
    //ћетод возвращает сумму, котора€ у клиента осталась на счету после сн€ти€
    // роме проверки счета, метод так же должен провер€ть достаточно ли денег в самом банкомате
    //≈сли недостаточно денег на счете, то должно генерироватьс€ исключение NotEnoughMoneyInAccount 
    //≈сли недостаточно денег в банкомате, то должно генерироватьс€ исключение NotEnoughMoneyInATM 
    //ѕри успешном сн€тии денег, указанна€ сумма должна списыватьс€ со счета, и в банкомате должно уменьшатьс€ количество денег
    public double getCash(double amount) throws NoCardInsertedException, NotEnoughtMoneyInAccountException, NotEnoughtMoneyInATMexception {
    	if (card == null) 
			throw new NoCardInsertedException("There is no card inserted");
    	if (getMoneyInATM() < amount) 
			throw new NotEnoughtMoneyInATMexception("Not enought money in ATM");
		if (checkBalance() < amount) 
			throw new NotEnoughtMoneyInAccountException("Not enought money on your card");
		
		card.getAccount().withdrow(amount);
		moneyInATM -= amount;
		return card.getAccount().getBalance();
    }
}
