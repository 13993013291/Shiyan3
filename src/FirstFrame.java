
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

import javax.script.*;
import javax.swing.*;

public class FirstFrame{
	public static JLabel l1=new JLabel("");
	private static int One[][] = new int[1000][1000];// ����������Ŀ������������ά�����У���ά�����ÿһ�ж����һ����
	private static double answer[]=new double[10001];//��Ŵ𰸵�����
	private static int temp[] = new int[10001];
	private static char symbol[] = new char[] { '+', '-', '*', '/' };// �����
	private static int realCount = 0;// ��ʾ"�Ѳ����Ĳ����ص���Ŀ"�ĸ���
	private static int count = 0;
	private static int number = 22;// ��Ŀ����
	private static int range = 9;// ��Ŀ��Χ
	private static int oneIndex = 1;
	private static int hehe = 1;
	private static int tempIndex = 1;
	private static int remainmember = 2;// ��Ŀǰ���������Ԫ�ء��ĸ���
	private static int used[]=new int[10001];//���One[]ÿ�е���Ч����
	private static int thecount=0;//GUI�������Ŀ��
	private static int five=0;
	private static int anscount=0;
	private static int totalpage=0;//��ҳ��
	private static int page=0;//��ǰҳ��
	private static int []correct=new int[10001];
	private static String before="999";//�ۼƴ������
	private static long timetime;
	
	public static void main(String args[]) throws IOException {
		File file1 = new File("Exercises.txt");
		PrintWriter pw1 = new PrintWriter(file1);
		File file2 = new File("answer.txt");
		PrintWriter pw2 = new PrintWriter(file2);
		myGUI();
	}
	// ����һ�����������Ŀ����>remainmember��2��1
	public static void One() throws IOException {
		hehe=9;
		for (int i = 0; realCount < number; i++) {// ���Ѳ�������Ŀ����<����Ҫ����Ŀ����
			if (realCount == 0) {// ��Ŀ��=0ʱ
				One[0][0] = new Random().nextInt(range);// ������һ��Ԫ��
				twoPlus(remainmember);// ִ�к󣬲���һ���������һ��Ԫ��
				used[realCount]=(remainmember*2-1);
				realCount++;// ��Ŀ��+1
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
				tempPlus(remainmember);// ������temp[]���һ��"�����ص���Ŀ"
				if (!compare2()) {// ���أ����ظ��򷵻�false
					One[realCount][0] = temp[0];// ��temp[]�����Ŀ���롰�Ѳ������� One[]��
					twoWithTemp(remainmember);// ��temp[]�����Ŀ���롰�Ѳ������� One[]��
					used[realCount]=(remainmember*2-1);//2-3  3-5  4-7 5-9
					realCount++;// ��Ŀ����+1
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

	// ����������������Ŀ
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

	// �ж���Ŀ�Ƿ��ظ�
	public static boolean compare2() {
		int arr2[][] = new int[10001][10000];
		int temp2[] = new int[10000];
		for (int i = 0; i < realCount; i++) {// ������ά����
			arr2[i] = One[i].clone();
		}
		temp2 = temp.clone(); // ������ʱ����
		for (int i = 0; i < realCount; i++) { // ��ά��������
			Arrays.sort(arr2[i]);
		}
		Arrays.sort(temp2); // ��ʱ��������
		for (int i = 0; i < realCount; i++) {
			if (Arrays.equals(temp2, arr2[i])) {
				return true;
			} else
				continue;
		}

		return false;
	}

	// ���鸳ֵ
	public static int twoPlus(int remainmember) {
		if (remainmember == 1) { // �ݹ��˳������� "Ŀǰ��Ҫ������Ԫ��"=1
			oneIndex = 1;// �±�ָ�
			return 0;
		}
		// ÿִ��һ��else��������һ�������������һ����Ԫ�ء�
		else {
			One[realCount][oneIndex] = symbol[new Random().nextInt(4)];// ������������������ά����
			One[realCount][oneIndex + 1] = new Random().nextInt(range);// ��������Ԫ�ط����ά����
			oneIndex += 2;// ��ά��������±�+2
			return twoPlus(remainmember - 1);// ��Ŀǰ���������Ԫ�ء��ĸ��� ��1
		}
	}

	// ���鸳ֵ
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

	// ����ϲ�
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

	// ��ȡtxt
	//�Ƚ���Ŀ��ӡ��txt�ļ������ô˷�����ȡtxt�ļ������Ŀ������𰸣����������������̨�Լ���txt�ļ�
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
	
	// ����ʹ�ӡ��
	public static void calculateAndPrint(String s,int inn) throws IOException {
		ScriptEngine se = new ScriptEngineManager().getEngineByName("JavaScript");
		String str = s;
		try {
			Integer i = 10;
			double tmp = Double.valueOf(i.toString());
			double d = Double.valueOf(se.eval(str).toString());
			answer[inn-2]=d;//�𰸷Ž�����
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
		//��ʱ������
		class shishi3 extends Thread {
		    private long noww = 0l;
		    private long start = System.currentTimeMillis();// ��������ʱ��ĺ���ֵ
		    private long time;
		    public void run() {
		    	int i=0;
		        while (true) {
		            noww = System.currentTimeMillis();// ��ȡһ��֮��ĺ���ֵ
		            time = noww - start;// ����ʱ������ĵ������
		            timetime=time;
		            String ssss=String.format("%02d:%02d:%02d\n",
		                    time / (1000 * 60 * 60) % 60/* ʱ */, 
		                    time / (1000 * 60)% 60/* �� */, 
		                    (time / 1000 % 60)/* �� */);// ��ʽ���ַ������
		       
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
		JFrame frame=new JFrame("������������");//����һ�����(����)
		myLayout(frame,2);//����
		frame.setSize(600,700);
		frame.setLocationRelativeTo(null);//��ܾ���
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���رտ��ʱ�������˽���
		//���123
		JPanel j1=new JPanel();
		frame.add(j1);
		JPanel j2=new JPanel();
		j2.setLayout(new GridLayout(0, 6,1,22));//6�У���ţ���Ŀ���𰸣��ύ,����,��ȷ��
		frame.add(j2);
		JPanel j3=new JPanel();
		frame.add(j3);
		//����  (���1)
		JLabel jj1=new JLabel("��Ŀ����");
		j1.add(jj1);
		jj1.setFont(new Font("",1,22));
		JTextField timu=new JTextField(5);
		j1.add(timu);
		JLabel jj2=new JLabel("�������뷶Χ��");
		j1.add(jj2);
		jj2.setFont(new Font("",1,22));
		JTextField fanwei=new JTextField(5);
		j1.add(fanwei);
		//ȷ����ť�����1��
		JButton b1=new JButton("��ʼ");
		b1.setFont(new java.awt.Font("����",0,22));
		j1.add(b1);
		//���1-��ʱ��ǩ
		j1.add(l1);
		//���3
		j3.add(new JLabel("������������������"));
		JButton b2=new JButton("");
		b2.setFont(new java.awt.Font("����",0,22));
		j3.add(b2);
		JButton b3=new JButton("");
		b3.setFont(new java.awt.Font("����",0,22));
		j3.add(b3);
		JButton b4 = new JButton("");
		b4.setFont(new java.awt.Font("����",0,22));
		j3.add(b4);
		j3.add(new JLabel("��������"));
		j3.add(new JLabel("������������������������"));
		JLabel j4=new JLabel("");
		j3.add(j4);
		j4.setFont(new Font("",1,33));
		//���2
		
		JLabel tihaoLabel1=new JLabel("");//��ű�ǩ
		j2.add(tihaoLabel1);
		JLabel tihaoLabel2=new JLabel("");//��ű�ǩ
		j2.add(tihaoLabel2);
		JLabel tihaoLabel3=new JLabel("");//��ű�ǩ
		j2.add(tihaoLabel3);
		JLabel tihaoLabel4=new JLabel("");//��ű�ǩ
		j2.add(tihaoLabel4);
		JLabel tihaoLabel5=new JLabel("");//��ű�ǩ
		j2.add(tihaoLabel5);
		JLabel tihaoLabel6=new JLabel("");//��ű�ǩ
		j2.add(tihaoLabel6);
		b1.addActionListener(new ActionListener(){//ȷ����ť
			public void actionPerformed(ActionEvent e){
				
				number=Integer.parseInt(timu.getText());
				range=Integer.parseInt(fanwei.getText());
				b2.setText("��һҳ");
				b3.setText("����");
				b4.setText("ͳ��");
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
				tihaoLabel1.setText("��������������");
				tihaoLabel2.setText("��������������");
				tihaoLabel3.setText("��������������");
				tihaoLabel4.setText("��������������");
				tihaoLabel5.setText("��������������");
				tihaoLabel6.setText("��������������");
				for(int i=thecount;i<number;i++) {
					five++;
					thecount++;
					JLabel tihaoLabel=new JLabel("��"+(i+1)+"��:");
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
					JTextField daanTextField=new JTextField(2);//����𰸿�
					j2.add(daanTextField);
					JButton tijiaoButton=new JButton("�ύ");//�ύ��ť
					j2.add(tijiaoButton);
					JLabel zhengwuLabel=new JLabel("");//�����ǩ
					j2.add(zhengwuLabel);
					zhengwuLabel.setFont(new Font("",1,22));
					JLabel zhengqueLabel=new JLabel("");//��ȷ�𰸱�ǩ
					j2.add(zhengqueLabel);				
					tijiaoButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							double daan=Double.parseDouble(daanTextField.getText());							
							if(daan==answer[anscount]) {
								zhengwuLabel.setText("��ȷ");
								zhengwuLabel.setForeground(Color.green);
								zhengqueLabel.setText("���ǣ�"+answer[anscount]);
								System.out.println(anscount);
								correct[anscount]=1;//���Ϊ1
							}else {
								zhengwuLabel.setText("����");
								zhengwuLabel.setForeground(Color.RED);
								zhengqueLabel.setText("���ǣ�"+answer[anscount]);
							}
							anscount++;
						}
					});
					j4.setText("��ǰҳ����"+(page+1)+"/"+(totalpage+1));
					if(five==5) {
						five=0;
						break;
					}
				}
				
			}
		});		
b2.addActionListener(new ActionListener() {//��һҳ
			public void actionPerformed(ActionEvent e) {
				page++;
				if(page<=totalpage) {
					j2.removeAll();
					j2.updateUI();
					tihaoLabel1.setText("��������������");
					tihaoLabel2.setText("��������������");
					tihaoLabel3.setText("��������������");
					tihaoLabel4.setText("��������������");
					tihaoLabel5.setText("��������������");
					tihaoLabel6.setText("��������������");
					for(int i=thecount;i<number;i++) {
						five++;
						thecount++;
						JLabel tihaoLabel=new JLabel("��"+(i+1)+"��:");
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
						JTextField daanTextField=new JTextField(2);//����𰸿�
						j2.add(daanTextField);
						JButton tijiaoButton=new JButton("�ύ");//�ύ��ť
						j2.add(tijiaoButton);
						JLabel zhengwuLabel=new JLabel("");//�����ǩ
						j2.add(zhengwuLabel);
						JLabel zhengqueLabel=new JLabel("");//��ȷ�𰸱�ǩ
						j2.add(zhengqueLabel);
						tijiaoButton.addActionListener(new ActionListener() {//�ύ��ť
							public void actionPerformed(ActionEvent e) {
								double daan=Double.parseDouble(daanTextField.getText());
								if(daan==answer[anscount]) {
									zhengwuLabel.setText("��ȷ");
									zhengwuLabel.setForeground(Color.green);
									zhengqueLabel.setText("���ǣ�"+answer[anscount]);
									System.out.println(anscount);
									correct[anscount]=1;//���Ϊ1
								}else {
									zhengwuLabel.setText("����");
									zhengwuLabel.setForeground(Color.RED);
									zhengqueLabel.setText("���ǣ�"+answer[anscount]);
								}
								anscount++;
							}
						});
						j4.setText("��ǰҳ����"+(page+1)+"/"+(totalpage+1));
						if(five==5) {
							five=0;
							break;
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "�ѵ����һҳ");
				}
			}
		});
		frame.setVisible(true);//��ʾ���
		b3.addActionListener(new ActionListener() {//������ť
			public void actionPerformed(ActionEvent e) {
				shishi33.stop();
				String co="��ԣ�";
				String fi="���";
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
				//���ļ���ȡ��ʷ��¼
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
				JOptionPane.showMessageDialog(null, co+"\n"+fi+"\n"+"��ʱ:"+(timetime / 1000 % 60)+"��\n"+"���δ��:"+now+"��"+"\n");
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
	//���ò��ֹ�����
	public static void myLayout(JFrame frame,int sort) {
		if(sort==1) {
			FlowLayout fly=new FlowLayout(FlowLayout.LEFT,20,20) ;//����룬ˮƽ�������ֱ���
			frame.setLayout(fly);
		}
		else if(sort==2) {
			GridLayout gly=new GridLayout(3,0);//3��2��
			frame.setLayout(gly);
		}
	}
}


