
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class zhuzhuangtu extends JFrame 
{
	private static final int DEFAULT_WIDTH=500;
	private static final int DEFAULT_HEIGHT=600;
	int[] save = new int[4];
	public zhuzhuangtu()
	{		
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("��������");
		setLocation(500,100);
		setVisible(true);
	}
	public void paint(Graphics g)
    {  
	    File file = new File("tongji.txt");
		StringBuilder result = new StringBuilder();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(file));//����һ��BufferedReader������ȡ�ļ�
            String s = new String();
            do
            {//ʹ��readLine������һ�ζ�һ��
            	for(int i=0;i<4;i++)
            	{
            		s = br.readLine() ;
            		save[i] = Integer.parseInt(s);
            		System.out.println(save[i]);
            	}
                
            }while((s = br.readLine())!=null);
            br.close();    
        }catch(Exception e){
            e.printStackTrace();
        }        						
        int Width = 400;
        int Height = 450; 
        Random ran = new Random();
        int leftMargin = 15;//����ͼ��߽�  
        int topMargin = 50;//����ͼ�ϱ߽�  
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(50, 100);
        int ruler = Height-topMargin-5;  
        int rulerStep = ruler/10;//����ǰ�ĸ߶�����Ϊ10����λ  
        g2.setColor(Color.WHITE);//���ư�ɫ����  
        g2.fillRect(0, 0, Width, Height);//���ƾ���ͼ  
        g2.setColor(Color.LIGHT_GRAY);  
        for(int i=0;i<=10;i++){//���ƻ�ɫ���ߺͰٷֱ�  
            g2.drawString((100-10*i)+"", 10, topMargin+rulerStep*i);//д�°ٷֱ�  
            g2.drawLine(10, topMargin+rulerStep*i, Width, topMargin+rulerStep*i);//���ƻ�ɫ����  
        }  
        g2.setColor(Color.PINK);  
        for(int i=0;i<4;i++){//��������ͼ  
            int value =save[i]*4*5;// ran.nextInt(Height-topMargin-10)+10;//����������εİٷֱ�  
            int step = (i+1)*35;//����ÿ������ͼ��ˮƽ���Ϊ40  
            //���ƾ���  
            g2.fillRoundRect(leftMargin+step*2, Height-value-10, 40, value, 40, 10); 
            //g2.fillRoundRect(leftMargin+step*2, Height-value, 40, value, 40, 10);  
            //�г���Ʒ�ı��  
            g2.drawString("����"+(i+1), leftMargin+step*2, Height-value-15);    
        } 
        JPanel south = new JPanel();		
		JButton btn2 = new JButton("����");
		south.add(btn2);
		add(south,BorderLayout.SOUTH);				 		 
		 btn2.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{	
					System.exit(0);
				}
			});		                            
    }  	
}
