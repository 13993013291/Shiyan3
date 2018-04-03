
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

import javax.script.*;
import javax.swing.*;

public class FirstFrame{
	public static JLabel l1=new JLabel("");
	private static int One[][] = new int[1000][1000];// 将产生的题目都存放在这个二维数组中，二维数组的每一行都存放一道题
	private static double answer[]=new double[10001];//存放答案的数组
	private static int temp[] = new int[10001];
	private static char symbol[] = new char[] { '+', '-', '*', '/' };// 运算符
	private static int realCount = 0;// 表示"已产生的并查重的题目"的个数
	private static int count = 0;
	private static int number = 22;// 题目个数
	private static int range = 9;// 题目范围
	private static int oneIndex = 1;
	private static int hehe = 1;
	private static int tempIndex = 1;
	private static int remainmember = 2;// “目前仍需产生的元素”的个数
	private static int used[]=new int[10001];//存放One[]每行的有效长度
	private static int thecount=0;//GUI的输出题目数
	private static int five=0;
	private static int anscount=0;
	private static int totalpage=0;//总页数
	private static int page=0;//当前页数
	private static int []correct=new int[10001];
	private static String before="999";//累计答对题数
	private static long timetime;
	
	public static void main(String args[]) throws IOException {
		File file1 = new File("Exercises.txt");
		PrintWriter pw1 = new PrintWriter(file1);
		File file2 = new File("answer.txt");
		PrintWriter pw2 = new PrintWriter(file2);
		myGUI();
	}
	// 产生一个运算符的题目——>remainmember由2到1
	public static void One() throws IOException {
		hehe=9;
		for (int i = 0; realCount < number; i++) {// 当已产生的题目个数<共需要的题目个数
			if (realCount == 0) {// 题目数=0时
				One[0][0] = new Random().nextInt(range);// 产生第一个元素
				twoPlus(remainmember);// 执行后，产生一个运算符和一个元素
				used[realCount]=(remainmember*2-1);
				realCount++;// 题目数+1
				File file = new File("Exercises.txt");
				FileWriter fw = new FileWriter(file, true);
				PrintWriter pw = new PrintWriter(fw);
				pw.print(
				        realCount + "." + One[realCount - 1][0] + (char) One[realCount - 1][1] + One[realCount - 1][2]);
				pw.println();
				pw.close();
			  }
			else {
				temp[0] = new Random().nextInt(range);
				tempPlus(remainmember);// 用数组temp[]存放一个"待查重的题目"
				if (!compare2()) {// 查重，不重复则返回false
					One[realCount][0] = temp[0];// 将temp[]里的题目放入“已查重数组 One[]”
					twoWithTemp(remainmember);// 将temp[]里的题目放入“已查重数组 One[]”
					used[realCount]=(remainmember*2-1);//2-3  3-5  4-7 5-9
					realCount++;// 题目个数+1
					File file = new File("Exercises.txt");
					FileWriter fw = new FileWriter(file, true);
					PrintWriter pw = new PrintWriter(fw);
					pw.print(realCount + "." + One[realCount - 1][0] + (char) One[realCount - 1][1]
							+ One[realCount - 1][2]);
					pw.println();
					pw.close();
				}
			}
			if (i == 2) {
				remainmember++;
				oneMore(number - realCount);
			}
		}
		File file = new File("Exercises.txt");
		readFile(file);
	}

	// 产生多个运算符的题目
	public static void oneMore(int remainNumber) throws IOException {
		int index = 1;
		if (realCount == number)
			return;
		else {
			count = 0;
			for (int i = 0; count < remainNumber; i++) {
				if (i == 0) {
					One[realCount][0] = new Random().nextInt(range);
					twoPlus(remainmember);
					count++;
					used[realCount]=(remainmember*2-1);
					realCount++;
					File file = new File("Exercises.txt");
					FileWriter fw = new FileWriter(file, true);
					PrintWriter pw = new PrintWriter(fw);
					pw.print(realCount + "." + One[realCount - 1][0]);
					for (int i3 = 0; i3 < remainmember - 1; i3++) {
						pw.print((char) One[realCount - 1][index]);
						pw.print(One[realCount - 1][index + 1]);
						index += 2;
					}
					pw.println();
					pw.close();
					index = 1;
				} 
				else {
					temp[0] = new Random().nextInt(range);
					tempPlus(remainmember);
					if (!compare2()) {
						One[realCount][0] = temp[0];
						twoWithTemp(remainmember);
						count++;
						used[realCount]=(remainmember*2-1);
						realCount++;
						File file = new File("Exercises.txt");
						FileWriter fw = new FileWriter(file, true);
						PrintWriter pw = new PrintWriter(fw);
						pw.print(realCount + "." + One[realCount - 1][0]);
						for (int i3 = 0; i3 < remainmember - 1; i3++) {
							pw.print((char) One[realCount - 1][index]);
							pw.print(One[realCount - 1][index + 1]);
							index += 2;
						}
						pw.println();
						pw.close();
						index = 1;
					}

				}
				if (i == 3) {
					remainmember++;
					oneMore(number - realCount);
					break;
				}
			}
		}
	}

	// 判断题目是否重复
	public static boolean compare2() {
		int arr2[][] = new int[10001][10000];
		int temp2[] = new int[10000];
		for (int i = 0; i < realCount; i++) {// 拷贝二维数组
			arr2[i] = One[i].clone();
		}
		temp2 = temp.clone(); // 拷贝临时数组
		for (int i = 0; i < realCount; i++) { // 二维数组排序
			Arrays.sort(arr2[i]);
		}
		Arrays.sort(temp2); // 临时数组排序
		for (int i = 0; i < realCount; i++) {
			if (Arrays.equals(temp2, arr2[i])) {
				return true;
			} else
				continue;
		}

		return false;
	}

	// 数组赋值
	public static int twoPlus(int remainmember) {
		if (remainmember == 1) { // 递归退出条件： "目前需要产生的元素"=1
			oneIndex = 1;// 下标恢复
			return 0;
		}
		// 每执行一次else，都产生一个“运算符”和一个“元素”
		else {
			One[realCount][oneIndex] = symbol[new Random().nextInt(4)];// 将产生的运算符放入二维数组
			One[realCount][oneIndex + 1] = new Random().nextInt(range);// 将产生的元素放入二维数组
			oneIndex += 2;// 二维数组的列下标+2
			return twoPlus(remainmember - 1);// “目前仍需产生的元素”的个数 减1
		}
	}

	// 数组赋值
	public static int tempPlus(int remainmember) {
		if (remainmember == 1) {
			tempIndex = 1;
			return 0;
		} else {
			temp[tempIndex] = symbol[new Random().nextInt(4)];
			temp[tempIndex + 1] = new Random().nextInt(range);
			tempIndex += 2;
			return tempPlus(remainmember - 1);
		}
	}

	// 数组合并
	public static int twoWithTemp(int remainmember) {
		if (remainmember == 1) {
			oneIndex = 1;
			return 0;
		} else {
			One[realCount][oneIndex] = temp[oneIndex];
			One[realCount][oneIndex + 1] = temp[oneIndex + 1];
			oneIndex += 2;
			return twoWithTemp(remainmember - 1);
		}
	}

	// 读取txt
	//先将题目打印至txt文件，再用此方法读取txt文件里的题目，计算答案，并将答案输出至控制台以及新txt文件
	public static String readFile(File file) {
		StringBuilder result = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String s = null;
			int i = 1;
			while ((s = br.readLine()) != null) {
				i++;
				if (i <= 10) {
					String s1 = s.substring(2);
					calculateAndPrint(s1,i);
				} else if (i < 100) {
					String s2 = s.substring(3);
					calculateAndPrint(s2,i);
				} else if (i < 1000) {
					String s3 = s.substring(4);
					calculateAndPrint(s3,i);
				} else if (i < 10001) {
					String s4 = s.substring(5);
					calculateAndPrint(s4,i);
				}
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}
	
	// 计算和打印答案
	public static void calculateAndPrint(String s,int inn) throws IOException {
		ScriptEngine se = new ScriptEngineManager().getEngineByName("JavaScript");
		String str = s;
		try {
			Integer i = 10;
			double tmp = Double.valueOf(i.toString());
			double d = Double.valueOf(se.eval(str).toString());
			answer[inn-2]=d;//答案放进数组
			File file = new File("answer.txt");
			FileWriter fw = new FileWriter(file, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(d);
			pw.close();
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
	public  int[][] getTitle(){
		return this.One;
	}
	public int[] getTitleLength() {
		return this.used;
	}
	public double[] getAnswer() {
		return this.answer;
	}
	public void setNumberAndRange(int number,int range) {
		this.number=number;
		this.range=range;
	}
	//GUI
	public static void myGUI() throws IOException{
		//计时器代码
		class shishi3 extends Thread {
		    private long noww = 0l;
		    private long start = System.currentTimeMillis();// 程序启动时间的毫秒值
		    private long time;
		    public void run() {
		    	int i=0;
		        while (true) {
		            noww = System.currentTimeMillis();// 获取一秒之后的毫秒值
		            time = noww - start;// 两个时间相减的到毫秒差
		            timetime=time;
		            String ssss=String.format("%02d:%02d:%02d\n",
		                    time / (1000 * 60 * 60) % 60/* 时 */, 
		                    time / (1000 * 60)% 60/* 分 */, 
		                    (time / 1000 % 60)/* 秒 */);// 格式化字符串输出
		       
			            l1.setText(ssss);
			            i++;
			            try {
			                Thread.sleep(1000);
			            } catch (InterruptedException e) {
			                e.printStackTrace();
			            }
		            
		        }
		    }
		}
		shishi3 shishi33=new shishi3();
		l1.setFont(new Font("",100,50));
		shishi33.start();
		File file4 = new File("count.txt");
		if(!file4.exists()) {
		file4.createNewFile();
		}
		JFrame frame=new JFrame("简易四则运算");//创建一个框架(容器)
		myLayout(frame,2);//设置
		frame.setSize(600,700);
		frame.setLocationRelativeTo(null);//框架居中
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//当关闭框架时，结束此进程
		//面板123
		JPanel j1=new JPanel();
		frame.add(j1);
		JPanel j2=new JPanel();
		j2.setLayout(new GridLayout(0, 6,1,22));//6列，题号，题目，答案，提交,正误,正确答案
		frame.add(j2);
		JPanel j3=new JPanel();
		frame.add(j3);
		//输入  (面板1)
		JLabel jj1=new JLabel("题目数：");
		j1.add(jj1);
		jj1.setFont(new Font("",1,22));
		JTextField timu=new JTextField(5);
		j1.add(timu);
		JLabel jj2=new JLabel("数字输入范围：");
		j1.add(jj2);
		jj2.setFont(new Font("",1,22));
		JTextField fanwei=new JTextField(5);
		j1.add(fanwei);
		//确定按钮（面板1）
		JButton b1=new JButton("开始");
		b1.setFont(new java.awt.Font("宋体",0,22));
		j1.add(b1);
		//面板1-计时标签
		j1.add(l1);
		//面板3
		j3.add(new JLabel("—————————"));
		JButton b2=new JButton("");
		b2.setFont(new java.awt.Font("宋体",0,22));
		j3.add(b2);
		JButton b3=new JButton("");
		b3.setFont(new java.awt.Font("宋体",0,22));
		j3.add(b3);
		JButton b4 = new JButton("");
		b4.setFont(new java.awt.Font("宋体",0,22));
		j3.add(b4);
		j3.add(new JLabel("————"));
		j3.add(new JLabel("————————————"));
		JLabel j4=new JLabel("");
		j3.add(j4);
		j4.setFont(new Font("",1,33));
		//面板2
		
		JLabel tihaoLabel1=new JLabel("");//题号标签
		j2.add(tihaoLabel1);
		JLabel tihaoLabel2=new JLabel("");//题号标签
		j2.add(tihaoLabel2);
		JLabel tihaoLabel3=new JLabel("");//题号标签
		j2.add(tihaoLabel3);
		JLabel tihaoLabel4=new JLabel("");//题号标签
		j2.add(tihaoLabel4);
		JLabel tihaoLabel5=new JLabel("");//题号标签
		j2.add(tihaoLabel5);
		JLabel tihaoLabel6=new JLabel("");//题号标签
		j2.add(tihaoLabel6);
		b1.addActionListener(new ActionListener(){//确定按钮
			public void actionPerformed(ActionEvent e){
				
				number=Integer.parseInt(timu.getText());
				range=Integer.parseInt(fanwei.getText());
				b2.setText("下一页");
				b3.setText("结束");
				b4.setText("统计");
				j1.remove(b1);
				j1.updateUI();
				if(number==5)
					totalpage=0;
				else
					totalpage=number/5;
				try {
					One();
				} catch (IOException e1) {				
					e1.printStackTrace();
				}
				tihaoLabel1.setText("———————");
				tihaoLabel2.setText("———————");
				tihaoLabel3.setText("———————");
				tihaoLabel4.setText("———————");
				tihaoLabel5.setText("———————");
				tihaoLabel6.setText("———————");
				for(int i=thecount;i<number;i++) {
					five++;
					thecount++;
					JLabel tihaoLabel=new JLabel("第"+(i+1)+"题:");
					tihaoLabel.setFont(new Font("",1,22));					
					j2.add(tihaoLabel);
					String timuString="";
			    	for(int j=0;j<used[i];j++) {
			    		if(j%2==0) { 
			    			timuString+=String.valueOf(One[i][j]);
			    		}
			    		else { 
			    			timuString+=String.valueOf((char)One[i][j]);
			    		}
			    	}			    	
					JLabel timuLabel=new JLabel(timuString+"=");
					j2.add(timuLabel);
					timuLabel.setFont(new Font("",1,22));
					JTextField daanTextField=new JTextField(2);//输入答案框
					j2.add(daanTextField);
					JButton tijiaoButton=new JButton("提交");//提交按钮
					j2.add(tijiaoButton);
					JLabel zhengwuLabel=new JLabel("");//正误标签
					j2.add(zhengwuLabel);
					zhengwuLabel.setFont(new Font("",1,22));
					JLabel zhengqueLabel=new JLabel("");//正确答案标签
					j2.add(zhengqueLabel);				
					tijiaoButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							double daan=Double.parseDouble(daanTextField.getText());							
							if(daan==answer[anscount]) {
								zhengwuLabel.setText("正确");
								zhengwuLabel.setForeground(Color.green);
								zhengqueLabel.setText("答案是："+answer[anscount]);
								System.out.println(anscount);
								correct[anscount]=1;//答对为1
							}else {
								zhengwuLabel.setText("错误");
								zhengwuLabel.setForeground(Color.RED);
								zhengqueLabel.setText("答案是："+answer[anscount]);
							}
							anscount++;
						}
					});
					j4.setText("当前页数："+(page+1)+"/"+(totalpage+1));
					if(five==5) {
						five=0;
						break;
					}
				}
				
			}
		});		
b2.addActionListener(new ActionListener() {//下一页
			public void actionPerformed(ActionEvent e) {
				page++;
				if(page<=totalpage) {
					j2.removeAll();
					j2.updateUI();
					tihaoLabel1.setText("———————");
					tihaoLabel2.setText("———————");
					tihaoLabel3.setText("———————");
					tihaoLabel4.setText("———————");
					tihaoLabel5.setText("———————");
					tihaoLabel6.setText("———————");
					for(int i=thecount;i<number;i++) {
						five++;
						thecount++;
						JLabel tihaoLabel=new JLabel("第"+(i+1)+"题:");
						j2.add(tihaoLabel);
						tihaoLabel.setFont(new Font("",1,22));
						String timuString="";
				    	for(int j=0;j<used[i];j++) {
				    		if(j%2==0) { 
				    			timuString+=String.valueOf(One[i][j]);
				    		}
				    		else { 
				    			timuString+=String.valueOf((char)One[i][j]);
				    		}
				    	}
						JLabel timuLabel=new JLabel(timuString+"=");
						j2.add(timuLabel);
						timuLabel.setFont(new Font("",1,22));
						JTextField daanTextField=new JTextField(2);//输入答案框
						j2.add(daanTextField);
						JButton tijiaoButton=new JButton("提交");//提交按钮
						j2.add(tijiaoButton);
						JLabel zhengwuLabel=new JLabel("");//正误标签
						j2.add(zhengwuLabel);
						JLabel zhengqueLabel=new JLabel("");//正确答案标签
						j2.add(zhengqueLabel);
						tijiaoButton.addActionListener(new ActionListener() {//提交按钮
							public void actionPerformed(ActionEvent e) {
								double daan=Double.parseDouble(daanTextField.getText());
								if(daan==answer[anscount]) {
									zhengwuLabel.setText("正确");
									zhengwuLabel.setForeground(Color.green);
									zhengqueLabel.setText("答案是："+answer[anscount]);
									System.out.println(anscount);
									correct[anscount]=1;//答对为1
								}else {
									zhengwuLabel.setText("错误");
									zhengwuLabel.setForeground(Color.RED);
									zhengqueLabel.setText("答案是："+answer[anscount]);
								}
								anscount++;
							}
						});
						j4.setText("当前页数："+(page+1)+"/"+(totalpage+1));
						if(five==5) {
							five=0;
							break;
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "已到最后一页");
				}
			}
		});
		frame.setVisible(true);//显示框架
		b3.addActionListener(new ActionListener() {//结束按钮
			public void actionPerformed(ActionEvent e) {
				shishi33.stop();
				String co="答对：";
				String fi="答错：";
				int now=0;
				int totalright=0;
				for(int i=0;i<number;i++) {
					if(correct[i]==1) {
						co+=(i+1);
						co+=",";
						now++;
					}else{
						fi+=(i+1);
						fi+=",";
					}
				}
				File file3 = new File("count.txt");
				FileWriter fw3;
				StringBuilder result = new StringBuilder();
				System.out.println("try1");
				//从文件读取历史记录
				try {
					BufferedReader br3 = new BufferedReader(new FileReader(file3));
					String s = null;	
					before = br3.readLine();
					if(before==null) {
						totalright=now;
					}else {
						br3.close();	
					}
				
				} catch (Exception e3) {
					e3.printStackTrace();
				}
				System.out.println("try2");
				try {
					PrintWriter pw3 = new PrintWriter(file3);
					pw3.println(totalright);
					pw3.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, co+"\n"+fi+"\n"+"用时:"+(timetime / 1000 % 60)+"秒\n"+"本次答对:"+now+"题"+"\n");
				try {
				   	File f = new File("tongji.txt"); 
					FileWriter fw = new FileWriter(f,true);
					PrintWriter pw = new PrintWriter(fw);
					pw.println(now);               
					fw.close();
							
					} catch (IOException e1) {			
						e1.printStackTrace();
					}
			}
		});
		
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				zhuzhuangtu frame1 = new zhuzhuangtu();
				frame1.setVisible(true);
				frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}
	//设置布局管理器
	public static void myLayout(JFrame frame,int sort) {
		if(sort==1) {
			FlowLayout fly=new FlowLayout(FlowLayout.LEFT,20,20) ;//左对齐，水平间隔，垂直间隔
			frame.setLayout(fly);
		}
		else if(sort==2) {
			GridLayout gly=new GridLayout(3,0);//3行2列
			frame.setLayout(gly);
		}
	}
}


