package cn.bdqn.shopping.mapper;

import cn.bdqn.shopping.entity.Balance;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author PAG
 * @since 2023-01-14
 */
@Mapper
public interface BalanceMapper extends BaseMapper<Balance> {
//    public List<Balance> balanceInquiryId(Integer userId);
}
