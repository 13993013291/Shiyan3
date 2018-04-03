
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
		setTitle("四则运算");
		setLocation(500,100);
		setVisible(true);
	}
	public void paint(Graphics g)
    {  
	    File file = new File("tongji.txt");
		StringBuilder result = new StringBuilder();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = new String();
            do
            {//使用readLine方法，一次读一行
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
        int leftMargin = 15;//柱形图左边界  
        int topMargin = 50;//柱形图上边界  
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(50, 100);
        int ruler = Height-topMargin-5;  
        int rulerStep = ruler/10;//将当前的高度评分为10个单位  
        g2.setColor(Color.WHITE);//绘制白色背景  
        g2.fillRect(0, 0, Width, Height);//绘制矩形图  
        g2.setColor(Color.LIGHT_GRAY);  
        for(int i=0;i<=10;i++){//绘制灰色横线和百分比  
            g2.drawString((100-10*i)+"", 10, topMargin+rulerStep*i);//写下百分比  
            g2.drawLine(10, topMargin+rulerStep*i, Width, topMargin+rulerStep*i);//绘制灰色横线  
        }  
        g2.setColor(Color.PINK);  
        for(int i=0;i<4;i++){//绘制柱形图  
            int value =save[i]*4*5;// ran.nextInt(Height-topMargin-10)+10;//随机产生柱形的百分比  
            int step = (i+1)*35;//设置每隔柱形图的水平间隔为40  
            //绘制矩形  
            g2.fillRoundRect(leftMargin+step*2, Height-value-10, 40, value, 40, 10); 
            //g2.fillRoundRect(leftMargin+step*2, Height-value, 40, value, 40, 10);  
            //列出产品的编号  
            g2.drawString("测试"+(i+1), leftMargin+step*2, Height-value-15);    
        } 
        JPanel south = new JPanel();		
		JButton btn2 = new JButton("结束");
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
