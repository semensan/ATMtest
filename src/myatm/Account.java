package myatm;

public interface Account {

	//���������� ������ �� �����
    public double getBalance();

    //������� ��������� ����� �� �����
    //���������� �����, ������� ���� �����
    public double withdrow(double amount);
    
}
