/**
 * 版权所有：美创科技
 * 项目名称:leo
 * 创建者: liushuai
 * 创建日期: 2013-7-11
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2013-7-11
 */
package com.edao.codes.javase.number;

/**
 * @author liushuai
 *
 */
public class LongTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long[] mc = {3639155065212127368L, 
				85872433728344193L, 
				1767384690329158148L,
				181838067370575240L,
				3468618345900707845L,
				110340012180013826L,
				-4592226790633630202L,
				2384964450475118736L,
				-6903731786366647228L,
				36037611452016898L,
				2612087853936607248L,
				-8789750478573886942L,
				4620772972576311304L,
				-8520660375115262976L,
				4902173902406832648L,
				4473924};
		long[] mc1 = { -3929361404437031322L, 6668927704183441256L,
				2544798706912620849L, -5401547457686195657L, 5675921421074501226L,
				8103651486316843256L, 4402437747518071265L, -7727395862661510324L,
				6793373726404571955L, 6131377334161326824L, -7266110334079685533L,
				2419108547571964313L, 3719874826217506598L, 7222760492152480311L,
				3559506772120610214L, 255013683L };
		
		//System.out.println(getCount(mc));
		System.out.println(getCount(mc1));
	}
	
	public static int getCount(long[] mc) {
		int count = 0;
		for (long l : mc) {
			count += Long.bitCount(l);
			System.out.println(l + " binaryString : " + Long.toBinaryString(l) + " " + Long.toBinaryString(l).length());
		}
		return count;
	}

}
