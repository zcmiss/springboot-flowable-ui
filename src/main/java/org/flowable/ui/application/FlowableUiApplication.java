/* 根据 Apache 许可证 2.0 版（“许可证”）获得许可；
 * 除非遵守许可证，否则您不得使用此文件。
 * 您可以在以下位置获取许可证副本：
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * 除非适用法律要求或书面同意，否则软件
 * 根据许可证分发是在“按原样”基础上分发的，
 * 不提供任何类型的明示或暗示的保证或条件。
 * 请参阅许可证以了解管理权限的特定语言和
 * 许可证下的限制。
 */
package org.flowable.ui.application;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

/**
 * @author Filip Hrisafov
 */
@Configuration
public class FlowableUiApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FlowableUiApplication.class);
    }
}
