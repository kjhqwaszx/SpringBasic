package hello.core.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{
    

/**
 * DIP 위반. (추상(Interface)에만 의존해야한다.)
 *   MemberServiceImpl이 추상(MemberRepository[interface]), 구현체(MemoryMember~ ) 둘다에 의존한다.
*/

//    private final MemberRepository memberRepository= new MemoryMemberRepository();

    private final MemberRepository memberRepository;


    @Autowired  //의존성 자동 주입 MemberRepository를 찾아서 자동으로 주입해준다. ( MemoryMemberRepository 가 된다.)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }



    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //Configuration 싱글톤 테스트용
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
