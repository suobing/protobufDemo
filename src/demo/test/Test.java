package demo.test;

import java.util.List;
import com.google.protobuf.InvalidProtocolBufferException;
import sample.demo.ProtoDemo;
import sample.demo.ProtoDemo.Student;
import sample.demo.ProtoDemo.Student.PhoneNumber;

public class Test {
	
	/**
	 * protobuf��һ�����ݽ����ĸ�ʽ���Զ����Ƶĸ�ʽ�������ݽ�������Ҫ�������紫�䡢�����ļ������ݴ洢���������
	 * 
	 * �������Ǿͽ�ָ����ʽ����Ϣת�����ֽ���ʽ���ݣ�Ȼ���ֽ���ʽ���ݻָ���ָ����ʽ����Ϣ
	 * ���߿��Լ򵥵Ŀ���.proto�ļ����ɵ�ProtoDemo��Ľṹ
	 */
	public static void main(String[] args) {
	
		//���Student����
		//�����Student����������˽�л�������ͨ��ͨ��Student���ڲ���Builder������builder
		ProtoDemo.Student.Builder builder =  ProtoDemo.Student.newBuilder();
		//Student��δ�ṩ������Ե�set��������Student���ڲ���builder�ṩ�˹���Student������Ե�set����
		builder.setId(1);
		builder.setName("�賿1��21��");
		builder.setEmail("2474600377@qq.com");
		builder.setSex(ProtoDemo.Student.Sex.MAN);
		//��ͬ������ȡPhoneNumber����
		ProtoDemo.Student.PhoneNumber.Builder phb = ProtoDemo.Student.PhoneNumber.newBuilder();
		phb.setNumber("123456789");
		phb.setType(ProtoDemo.Student.PhoneType.HOME);
		PhoneNumber pn = phb.build();
		builder.addPhone(pn);
		//�����ٻ�ȡ��һ��PhoneNumber����ԭ��һ��
		PhoneNumber pn2 = ProtoDemo.Student.PhoneNumber.newBuilder()
				.setNumber("789456123").setType(ProtoDemo.Student.PhoneType.MOBILE).build();
		builder.addPhone(pn2);
		
		Student stu = builder.build();
		//���������ǽ���װ�����ݵĶ���ʵ����ת��Ϊ�ֽ����飬�������ݴ��䡢�洢��
		byte[] stuByte = stu.toByteArray();
		
		//����õ���stuBte�ֽ���������ǿ��Խ������ݽ������ݴ����洢������������ʲô��������͸��ݾ����������
		//��������stuBytͨ�����䣬����Ĵ���ӵ��˸�����
		
		//���շ� ,����Ϊ�˷������Ǿ�д��һ��������
		try {
			//���ֽ�����ת��Ϊ��Ӧ�Ķ���ʵ��
			Student stu2 = ProtoDemo.Student.parseFrom(stuByte);
			//����õ���Studentʵ���ˣ��Ϳ��Ը�����Ҫ�����������������
			System.out.println("ѧ��ID:"+stu2.getId());
			System.out.println("������"+stu2.getName());
			System.out.println("�Ա�"+(stu2.getSex().getNumber()==0?"��":"Ů"));
			System.out.println("���䣺"+stu2.getEmail());
			//����phoneNumber�ֶ�
			List<PhoneNumber> phList = stu2.getPhoneList();
			for (PhoneNumber p : phList) {
				System.out.println(p.getType().toString()+"�绰:"+p.getNumber());
			}
			
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
